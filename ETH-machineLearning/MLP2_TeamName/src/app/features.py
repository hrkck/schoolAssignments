# All feature extraction, preprocessing functions and their helpers (which should be place above the main functions)
# This also contains simple feature manipulation functions (top functions)
# A feature extraction function should be written like :
# def name_of_feature(images, **args=**defaults), INPUT as list of images, OUTPUT as np.array

import numpy as np
import scipy.ndimage
from sklearn.decomposition import PCA
from sklearn.feature_selection import VarianceThreshold

from loaders import exists_feature, save_feature, load_feature, compress


def center(features):
    """
    Center the given feature value by subtracting the mean. This can improve the condition (?) of the problem.
    :param features: features to manipulate
    :return: manipulated features
    """
    new_features = np.copy(features)
    for i in range(len(features[0])):
        m = np.mean(features[:, i])
        new_features[:, i] -= m
    return new_features


def unit_range(features):
    """
    Transform, by scaling and translation, the given features so their values lie in the interval [0,1]
    :param features: features to manipulate
    :return: manipulated features
    """
    new_features = np.copy(features)
    for i in range(len(new_features[0])):
        maxx = max(features[:, i])
        minn = min(features[:, i])
        new_features[:, i] = 2 * (features[:, i] - minn) / float(maxx - minn) - 1
    return new_features


def standardize(features):
    """
    Transform the given features so their mean is 0 and their variance is 1.
    :param features: features to manipulate
    :return: manipulated features
    """
    new_features = np.copy(features)
    for i in range(len(new_features[0])):
        v = np.var(features[:, i])
        m = np.mean(features[:, i])
        new_features[:, i] = (features[:, i] - m) / np.sqrt(v)
    return new_features


def sigmoid(features, b=1):
    """
    Transform the given features by performing a sigmoid operation on them.
    :param features: features to manipulate
    :param b: factor (see code)
    :return: manipulated features
    """
    return 1 / (1 + np.exp(b * features))


def log(features, b=0):
    """
    Take the logarithm of (b + feature_value).
    :param features:
    :param b: additive term (see code)
    :return: manipulated features
    """
    return np.log(b + features)


def spread(images):
    """
    Find occurences of each gray value in each image.
    :param images: numpy array of 4D matrices of .nii MRI images of brains.
    :return: array
    """
    features = np.zeros((len(images), 2000))
    for i in range(features.shape[0]):
        for x in range(0, len(images[0])):
            for y in range(0, len(images[0][0])):
                for z in range(0, len(images[0][0][0])):
                    gray_value = int(images[i][x][y][z][0])  # actually 4 dim array
                    if gray_value < features.shape[1]:
                        features[i][gray_value] += 1
        features[i][0] = 0  # set peak at black to zero, because is same for every image
    return features


def derivatives(images):
    """
    Calculates the difference between all neighboring voxels of each image
    :param images: numpy array of 4D matrices of .nii MRI images of brains.
    :return: array
    """
    features = np.empty((len(images), 4000))
    for i in range(len(images)):
        for x in range(1, len(images[0]) - 1):
            for y in range(0, len(images[0][0]) - 1):
                for z in range(0, len(images[0][0][0]) - 1):
                    derivatives = []
                    x1 = abs(images[i][x][y][z][0] - images[i][x - 1][y][z][0])
                    derivatives.append(x1)
                    x2 = abs(images[i][x][y][z][0] - images[i][x + 1][y][z][0])
                    derivatives.append(x2)
                    y1 = abs(images[i][x][y][z][0] - images[i][x][y - 1][z][0])
                    derivatives.append(y1)
                    y2 = abs(images[i][x][y][z][0] - images[i][x][y + 1][z][0])
                    derivatives.append(y2)
                    z1 = abs(images[i][x][y][z][0] - images[i][x][y][z - 1][0])
                    derivatives.append(z1)
                    z2 = abs(images[i][x][y][z][0] - images[i][x][y][z + 1][0])
                    derivatives.append(z2)
                    for derivative in derivatives:
                        if derivative < features.shape[1]:
                            features[i][derivative] += 1
        features[i][0] = 0
    return features


def sobel(images, feature_function=None):
    """
    Compute the magnitude of the gradient for every pixel by using the sobel masks in 3D. If supplied,
    a feature_function is applied to that image of gradients.
    :param images: the image samples
    :param feature_function: feature to use on the image of gradients
    :return: Extracted feature (feature_function) on the image of gradients.
    """
    if feature_function is None:
        feature_function = PCA_transform
    features = np.zeros(shape=images.shape)
    for i, image in enumerate(images):
        sx = scipy.ndimage.filters.sobel(image, axis=0)
        sy = scipy.ndimage.filters.sobel(image, axis=1)
        sz = scipy.ndimage.filters.sobel(image, axis=2)
        sob = np.sqrt(sx * sx + sy * sy + sz * sz)
        features[i] = sob
    features /= 50.0
    return feature_function(features)


def find_border_mask(image):
    """
    Find mask (image of zeros and ones in specific places) of the border of the human brain (skull) that can used to
    filter out specific voxels by multiplying.
    :param image: the image which mask you want to find
    :return: filtering mask of ones and zeros
    """
    mask = np.zeros(image.shape)
    for x in range(0, len(image)):
        for y in range(0, len(image[0])):
            for z in range(0, len(image[0][0])):
                if image[x][y][z][0] != 0:
                    mask[x][y][z][0] = 1
    return mask


def shrink(image, shrinkage):
    """
    Shrink a given image with a given shrinkage by first compressing it, and then padding it with zeros
    so new.shape = old.shape.
    :param image: image to shrink
    :param shrinkage: amount of shrinkage, factor
    :return: shrunk image
    """
    shrunk_image = compress(image, shrinkage)
    dx = len(image) - len(shrunk_image)
    dy = len(image[0]) - len(shrunk_image[0])
    dz = len(image[0][0]) - len(shrunk_image[0][0])
    shrunk_image = np.pad(shrunk_image, (
        (dx / 2, (dx / 2) + dx % 2), ((dy / 2), (dy / 2) + dy % 2), ((dz / 2), (dz / 2) + dz % 2), (0, 0)),
                          'constant', constant_values=0)
    return shrunk_image


def border_smooth_sample(images):
    """
    Calculate the smoothed version of the spread of the outer section (border) of the brain.
    :param images: numpy array of images
    :return: numpy array of features
    """
    # mask images
    masked_images = np.empty(images.shape)
    for i in range(0, len(images)):
        border_mask = find_border_mask(images[i])
        masked_images[i] = np.multiply(np.abs(np.subtract(border_mask, shrink(border_mask, 4))), images[i])
    return smooth_sample(masked_images)


def get_shaved_mask(mask, shaving):
    """
    Return the shaved version of the given mask. Shaving one time ('shaving' = 1) means trimming a one-pixel width
    edge of the mask.
    :param mask: the mask to trim
    :param shaving: amount of shaving, the mask is shaved 'shaving' times.
    :return: shaved mask
    """
    new_mask = np.copy(mask)
    for i in range(shaving):
        for x in range(0, len(mask)):
            for y in range(0, len(mask[0])):
                for z in range(0, len(mask[0][0])):
                    # check if voxel is edge voxel
                    if new_mask[x][y][z][0] == 1 and \
                            (new_mask[x + 1][y][z][0] == -i or new_mask[x - 1][y][z][0] == -i or \
                                         new_mask[x][y + 1][z][0] == -i or new_mask[x][y - 1][z][0] == -i or \
                                         new_mask[x][y][z + 1][0] == -i or new_mask[x][y][z - 1][0] == -i):
                        new_mask[x][y][z][0] = -i - 1
    np.place(new_mask, new_mask < 0, [0])
    return new_mask


def feature_on_cerebrum(images, shaving=12, height=45, feature_function=None):
    """
    Apply a given 'feature_function' on the cerebrum (approximately) part of the brain.
    :param images: image sample
    :param shaving: thickness of the cerebrum
    :param height: how deep the cerebrum is expected to go, in pixels
    :param feature_function: function to apply on the cerebrum
    :return: features of the cerebrum
    """
    if feature_function is None:
        feature_function = grey_white_ratio
    border_mask = find_border_mask(images[0])
    mask = border_mask - get_shaved_mask(border_mask, shaving)
    shp = images.shape
    masked_images = np.zeros(shape=(shp[0], shp[1], shp[2], height, shp[4]))
    for i in range(0, len(images)):
        tmp = mask * images[i]
        masked_images[i] = tmp[:, :, -height:]
    return feature_function(masked_images)


def feature_border_images(images, n_increments=3, start_shaving=0, difference_shaving=9, feature_function=None):
    """
    Extract a given feature from a given number (n_increments) of border_images.
    :param images: array of images
    :param n_increments: number of border images you take for each image
    :param start_shaving: the outer shaving of the first border image
    :param difference_shaving: this + 'start_shaving' represents the inner shaving of the last border image
    :param feature_function: the feature to execute on the border images
    :return: array of features
    """
    # default for functions are not possible...
    if feature_function is None:
        feature_function = grey_white_ratio

    step = difference_shaving / n_increments
    if step == 0:
        step = 1
        n_increments = difference_shaving
    difference_shaving = step * n_increments

    all_features = []
    mask = find_border_mask(images[0])
    shape = (n_increments,) + images[0].shape

    for i in range(0, len(images)):
        border_images = np.empty(shape=shape)
        # shaving masks
        for j, shaving in enumerate(range(start_shaving, start_shaving + difference_shaving, step)):
            border_images[j] = get_shaved_mask(mask, shaving)
        # making rings by subtracting masks
        for k in range(n_increments - 1):
            border_images[k] -= border_images[k + 1]
        border_images[-1] += get_shaved_mask(mask, start_shaving + difference_shaving)
        # masking images
        for l in range(n_increments):
            border_images[l] *= images[i]
        features = feature_function(border_images)
        features = np.concatenate(features)
        all_features.append(features)
    return np.array(all_features)


def grey_white_ratio(images, split=600):  # optimized
    """
    Return the ratio between the gray and white brain cells of the full brain.
    :param images: images to process
    :return: numpy array of ratios
    """
    spreads = spread(images)
    return np.array([[np.sum(spr[0:split]) / (np.sum(spr[split:]) + 0.1)] for spr in spreads])


def polyfit(images, deg=6):  # optimized
    """
    Polynomial fitting of the spread of the image at a given degree 'deg'.
    :param images: numpy array of 4D matrices of .nii MRI images of brains.
    :param deg: degrees of polynomial fitting
    :return: numpy array
    """
    spreads = spread(images)
    p = np.empty((len(images), deg + 1))
    for i in range(len(images)):
        p[i] = np.polyfit(
            range(0, len(spreads[i])),
            spreads[i],
            deg)
    return p


def smooth(x, window_len=101, window='hanning'):
    """
    Smooth the data using a window with requested size.
    :param x: the input signal
    :param window_len: the dimension of the smoothing window; should be an odd integer
    :param window:  the type of window from 'flat', 'hanning', 'hamming', 'bartlett', 'blackman'
            flat window will produce a moving average smoothing.
    :return: Smoothed data (same dimension)
    """
    # for continuity (but not really) around the edges of the data, assume x = abcde, s = ba + abcde + ed
    s = np.r_[x[window_len - 1:0:-1], x, x[-2:-window_len - 1:-1]]

    if window == 'flat':  # moving average
        w = np.ones(window_len, 'd')
    else:
        w = eval('np.' + window + '(window_len)')

    y = np.convolve(w / w.sum(), s, mode='valid')

    # length(output) != length(input), to correct this: return y[((window_len - 1) / 2):-(window_len + 1 / 2)] instead
    # of just y.
    return y[((window_len - 1) / 2):-((window_len + 1) / 2) + 1]


def fourier(images, number_frequencies=4, manipulation=None):  # optimized
    """
    Calculates the fourier components of the histogram of the given images
    :param images: numpy array of 4D matrices of .nii MRI images of brains.
    :param number_frequencies: the number of frequencies returned
    :param manipulation: a data manipulation to use on the calculated frequencies
    :return: array
    """
    if manipulation is None:
        manipulation = lambda x:x
    spread_data = spread(images)
    features = np.zeros((len(images), number_frequencies))
    for (i, spreaded) in enumerate(spread_data):
        frequencies = map(abs, np.fft.fft(spreaded))
        frequencies = frequencies[0:number_frequencies]
        features[i] = frequencies
    return manipulation(features)


def sample_curve(smoothed, number_of_samples):
    """
    Sampling given data
    :param smoothed: spread feature data after smoothing
    :param number_of_samples: integer
    :return: samples as a list
    """
    result = list()
    for i in range(0, len(smoothed), int(np.round(len(smoothed) / float(number_of_samples)))):
        result.append(smoothed[i])
    return result


def smooth_sample(images, number_samples=25, window_len=151, window='hanning', region=0):
    """
    Smooths and samples given spread data of brain images.
    :param images: images to process
    :param number_samples: number of samples to take from the smoothed curve
    :param window_len: the length of the convolution window
    :param window: the type of convolution window
    :param region: defines the region of the brain that is used for the spread function (inner, outer, or all)
    :return: Feature as a numpy array
    """
    # if region == 1:
    #     spread_data = spread_of_outer_region(images)
    # elif region == 2:
    #     spread_data = spread_of_inner_region(images)
    # else:
    spread_data = spread(images)
    features = []
    for spreaded in spread_data:
        smoothed = smooth(spreaded, window_len=window_len, window=window)
        sampled = sample_curve(smoothed, number_samples)
        features.append(sampled)
    return np.array(features)


def mean(x):
    """
    Returns the mean of the array interpreted as a histogram.
    :param x: input array
    :return: mean
    """
    total_number_pixels = reduce(lambda a, b: a + b, x)
    if total_number_pixels == 0:
        return 0
    return reduce(lambda a, b: a + b, map(lambda (i, el): i * el, enumerate(x))) / float(total_number_pixels)


def mean_feature(images):
    """
    Finds the means of all the images.
    :param images: numpy array of 4D matrices of .nii MRI images of brains.
    :return: array
    """
    spread_data = spread(images)
    features = np.empty((len(images), 1))
    for (i, spreaded) in enumerate(spread_data):
        smoothed = smooth(spreaded)
        current_mean = mean(smoothed)
        features[i][0] = current_mean
    return features


def median(x):
    """
    Returns the median of the array interpreted as a histogram.
    :param x: input array
    :return: median
    """
    total_number_pixels = float(sum(x))
    position = 0
    counter = 0
    while (counter < total_number_pixels / 2):
        counter += x[position]
        position += 1
    return position


def median_feature(images):
    """
    Finds the medians of all the images.
    :param images: numpy array of 4D matrices of .nii MRI images of brains.
    :return: array
    """
    spread_data = spread(images)
    features = np.empty((len(images), 1))
    for (i, spreaded) in enumerate(spread_data):
        smoothed = smooth(spreaded)
        current_median = median(smoothed)
        features[i][0] = current_median
    return features


def variance(x):
    """
    Returns the variance of the array interpreted as a histogram.
    :param x: input array
    :return: variance
    """
    total_number_pixels = reduce(lambda a, b: a + b, x)
    if total_number_pixels <= 1:
        return 0
    mean_x = mean(x)
    return reduce(lambda a, b: a + b, map(lambda (i, el): el * (i - mean_x) ** 2, enumerate(x))) / \
           float(total_number_pixels - 1)


def variance_feature(images):
    """
    Finds the variance of all the images.
    :param images: numpy array of 4D matrices of .nii MRI images of brains.
    :return: array
    """
    spread_data = spread(images)
    features = np.empty((len(images), 1))
    for (i, spreaded) in enumerate(spread_data):
        smoothed = smooth(spreaded)
        current_variance = variance(smoothed)
        features[i][0] = current_variance
    return features


def divide_image(image, x_cuts, y_cuts, z_cuts):
    """
    Divides an image in multiple sub images.
    :param image: the image to divide
    :param x_cuts: the number of pieces in x-direction
    :param y_cuts: the number of pieces in y-direction
    :param z_cuts: the number of pieces in z-direction
    :return: array of subimages
    """
    x_length = len(image) / x_cuts
    y_length = len(image[0]) / y_cuts
    z_length = len(image[0][0]) / z_cuts
    subimages = list()
    for x in range(0, x_cuts):
        for y in range(0, y_cuts):
            for z in range(0, z_cuts):
                subimage = image[x * x_length: (x + 1) * x_length, y * y_length:(y + 1) * y_length,
                           z * z_length:(z + 1) * z_length]
                subimages.append(subimage)
    return subimages


def feature_subimages(images, feature_function, x_cuts, y_cuts, z_cuts):
    """
    Extract a feature out of the subimages of the given images.
    :param images: numpy array of 4D matrices of .nii MRI images of brains.
    :param feature_function: the feature to extract
    :param x_cuts: the number of pieces in x-direction
    :param y_cuts: the number of pieces in y-direction
    :param z_cuts: the number of pieces in z-direction
    :return: feature on the subimages
    """
    features = []
    for image in images:
        subimages = divide_image(image, x_cuts, y_cuts, z_cuts)
        subimage_feature = feature_function(subimages)
        subimage_feature = np.concatenate(subimage_feature)
        features.append(subimage_feature)
    return np.array(features)


def find_local_maxima(x, number_maxima, number_neighbours):
    """
    Local maximum is defined as a variable in the array that is bigger than all of its right and left neighbors
    where the number of neighbors to look at is given as parameter.
    :param x: array
    :param number_maxima: The number of maxima that is sought
    :param number_neighbours: The number of values to the left and right that are less than the maximum
    :return: array of pairs: (the place of the maximum, value of maximum)
    """
    all_maxima = []
    for i in range(number_neighbours, len(x) - number_neighbours):
        if all(x[i] >= a for a in x[i - number_neighbours:i]) and all(
                        x[i] >= a for a in x[i + 1:i + number_neighbours + 1]):
            all_maxima.append((i, x[i]))
    if len(all_maxima) <= number_maxima:
        all_maxima = all_maxima + ([(0, 0)] * (number_maxima - len(all_maxima)))
        return all_maxima
    else:
        all_maxima.sort(lambda a, b: 1 if a[1] < b[1] else (0 if a[1] == b[1] else -1))
        return all_maxima[:number_maxima]


def local_maxima_3(images):
    """
    Find the 3 highest local maxima of the density plot of different gray values of the images.
    :param images: numpy array of 4D matrices of .nii MRI images of brains.
    :return: array
    """
    spread_data = spread(images)
    features = np.empty((len(images), 6))
    for (i, spreaded) in enumerate(spread_data):
        smoothed = smooth(spreaded)
        maxima = find_local_maxima(smoothed, 3, 10)
        maxima = np.array(maxima).flat
        # Take the real value in stead of the smoothed maximum
        for j in range(1, len(maxima), 2):
            maxima[j] = spread_data[i][maxima[j - 1]]
        features[i] = maxima
    return features


def local_maxima_without_smoothing(images):
    """
    Finds the local maxima from the raw, unsmoothed data of the density plot of the images.
    :param images: numpy array of 4D matrices of .nii MRI images of brains.
    :return: array
    """
    spread_data = spread(images)
    features = np.empty((len(images), 10))
    for (i, spreaded) in enumerate(spread_data):
        maxima = find_local_maxima(spreaded, 5, 100)
        maxima = np.array(maxima).flat
        features[i] = maxima
    return features


def feature_on_cuboid(images, xbound=30, ymin=-30, ymax=50, zmin=-15, zmax=18, feature_function=None):
    """
    Apply a given 'feature_function' one a centered cuboid with the given dimension. All dimension are relative to
    the middle of the image.
    :param images: image samples
    :param xbound: dimensions in the x-direction = [-xbound, xbound]
    :param ymin: dimension in the y-direction = [-ymin, ymax]
    :param ymax: dimension in the y-direction = [-ymin, ymax]
    :param zmin: dimension in the z-direction = [-zmin, zmax]
    :param zmax: dimension in the z-direction = [-zmin, zmax]
    :param feature_function: function to apply on the cuboid
    :return: features on the cuboid
    """
    # default for functions are not possible...
    if feature_function is None:
        feature_function = grey_white_ratio
    center_images = np.empty(shape=(len(images), 2 * xbound, ymax - ymin, zmax - zmin, 1))
    shp = images[0].shape
    for i, image in enumerate(images):
        center_images[i] = \
            image[(-xbound + shp[0] / 2):(xbound + shp[0] / 2), \
            (ymin + shp[1] / 2):(ymax + shp[1] / 2), \
            (zmin + shp[2] / 2):(zmax + shp[2] / 2)]
    return feature_function(center_images)


def feature_on_cube(images, x_ratio=0.5, y_ratio=0.5, z_ratio=0.5, feature_function=None):
    """
    Apply a given feature_function on the cube with the given dimensions. The cube's center lies in the middle of
    the image.
    :param images: image samples
    :param x_ratio: width ratio, compared to the width of the image, in the x-direction
    :param y_ratio: width ratio, compared to the width of the image, in the y-direction
    :param z_ratio: width ratio, compared to the width of the image, in the z-direction
    :param feature_function: function to apply on the cubes
    :return: features on the cubes
    """
    # default for functions are not possible...
    if feature_function is None:
        feature_function = fourier
    shp = images[0].shape
    x = int(shp[0] * x_ratio / 2)
    y = int(shp[1] * y_ratio / 2)
    z = int(shp[2] * z_ratio / 2)
    center_images = np.empty(shape=(len(images), 2 * x, 2 * y, 2 * z, 1))
    for i, image in enumerate(images):
        center_images[i] = \
            image[(-x + shp[0] / 2):(x + shp[0] / 2), \
            (-y + shp[1] / 2):(y + shp[1] / 2), \
            (-z + shp[2] / 2):(z + shp[2] / 2)]
    return feature_function(center_images)


def feature_on_outer_region(images, a=9, b=11, c=9, feature_function=None):
    """
    Apply a given 'feature_function' on the outside region of each image.
    The outer region is defined as all pixels that lie out of a certain ellipsoid.
    :param images: numpy array of 4D matrices of .nii MRI images of brains.
    :param a: intercept of the ellipse on the x-axis
    :param b: intercept of the ellipse on the y-axis
    :param c: intercept of the ellipse on the z-axis
    :param feature_function: function to apply on the ellipses
    :return: array
    """
    # default for functions are not possible...
    if feature_function is None:
        feature_function == fourier
    center_images = np.copy(images)
    x_max = len(images[0])
    y_max = len(images[0][0])
    z_max = len(images[0][0][0])
    for i in range(center_images.shape[0]):
        for x in range(x_max):
            for y in range(y_max):
                for z in range(z_max):
                    if ((x - float(x_max / 2)) / a) ** 2 + \
                                    ((y - float(y_max / 2)) / b) ** 2 + \
                                    ((z - float(z_max / 2)) / c) ** 2 > 1:
                        center_images[i][x][y][z] = images[i][x][y][z]
    return feature_function(center_images)


def spread_of_inner_region(images):
    """
    Find occurences of each gray value in the inner region of each image.
    The inner region is defined as an ellipsoid around the center of the image.
    :param images: numpy array of 4D matrices of .nii MRI images of brains.
    :return: array
    """
    features = np.zeros((len(images), 2000))
    a = 5
    b = 7
    c = 4
    x_max = len(images[0])
    y_max = len(images[0][0])
    z_max = len(images[0][0][0])
    for i in range(features.shape[0]):
        for x in range(x_max):
            for y in range(y_max):
                for z in range(z_max):
                    if ((x - float(x_max / 2)) / a) ** 2 + \
                                    ((y - float(y_max / 2)) / b) ** 2 + \
                                    ((z - float(z_max / 2)) / c) ** 2 < 1:
                        gray_value = int(images[i][x][y][z][0])  # actually 4 dim array
                        if gray_value < features.shape[1]:
                            features[i][gray_value] += 1
        features[i][0] = 0
    return features


def PCA_transform(images, number_of_components=5):  # optimized
    """
    Compute the 'number_of_components' principal components of the spread of the given images.
    :param images: images to process
    :param number_of_components: number of components to retrieve
    :return: numpy array of features
    """
    smoothed_data = smooth_sample(images, 2000)
    pca = PCA(n_components=number_of_components)
    return pca.fit_transform(smoothed_data)


def preprocessing_variance_threshold(features):
    """
    Preprocessing step: Feature Selection by variance threshold
    :param features: The feature as a numpy array for the selection
    :return: The feature as a numpy array
    """
    var_threshold = VarianceThreshold(threshold=(.8 * (1 - .8)))
    return var_threshold.fit_transform(features)


def compute_features(features_functions, images, speedup=False):
    """
    Concantenating outputs of given list of features
    :param features_functions: features to compute
    :param images: numpy array of 4D matrixes of .nii MRI images of brains.
    :param speedup: only set this to true if you are computing features of all train images, then this will speedup the
    process by looking for features that have been saved.
    :return: concantenated numpy array of features
    """
    features = np.empty((len(images), 0))
    for f in features_functions:
        if speedup:
            if exists_feature(f):
                data = load_feature(f)
            else:
                data = f(images)
                save_feature(f, data)
        else:
            data = f(images)
        features = np.concatenate((features, data), axis=1)
    return features
