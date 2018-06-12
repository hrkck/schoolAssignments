# Tests and visualizations of features (no unittests).
# This folder doesn't have be documented heavily and can be a bit messy.

import math

import matplotlib.pyplot as plt
import nibabel as nib
import nilearn.plotting as nilplt

from app.features import *
from app.loaders import load_images, load_feature, load_ages, get_path


def polyfit_test():
    image = load_images(0, 1, False, 5)
    spr = spread(image)[0]
    p = polyfit(image, 10)[0]
    plt.scatter(range(0, len(spr)), spr)
    plt.scatter(range(0, len(spr)), np.polyval(p, range(0, len(spr))))
    plt.show()


def smooth_sample_test():
    image = load_images(0, 1, False, 5)
    spr = spread(image)[0]
    smoothed0 = smooth(spr, 51, "hanning")
    smoothed1 = smooth(spr, 51, "hanning")
    smoothed2 = smooth(spr, 25, "hanning")
    plt.scatter(range(0, len(spr)), spr)  # blue
    plt.scatter(range(0, len(spr)), smoothed1)  # black
    # plt.scatter(range(0, len(spr)), smoothed2)

    plt.show()


def find_local_maxima_test():
    images = load_images(0, 5, False, 5)
    spread_data = spread(images)
    maxima = local_maxima_without_smoothing(images)
    smoothed_maxima = local_maxima_3(images)
    print maxima
    print smoothed_maxima
    _, ax = plt.subplots(1, 5, sharey=True)
    ax = ax.flatten()
    for i in range(5):
        smoothed = smooth(spread_data[i], window_len=101)
        ax[i].scatter(range(len(spread_data[i])), spread_data[i], s=1, marker=",")
        ax[i].scatter(range(len(spread_data[i])), smoothed)
        ax[i].set_title(maxima[i])
    plt.ylim([0, 40])
    plt.show()


def mean_feature_test():
    image = load_images(0, 1, False, 5)
    means = mean_feature(image)
    print means


def local_maxima_3_test():
    image = load_images(0, 1, False, 5)
    maxima = local_maxima_3(image)
    print maxima


def spread_test():
    spreads = load_feature(spread)
    nr = 20
    ages = load_ages()

    plt.locator_params(axis='x', nbins=5)
    for j in range(0, 400, nr):
        _, ax = plt.subplots(int(math.ceil((2 / math.sqrt(6)) * math.sqrt(nr))),
                             int(math.ceil((3 / math.sqrt(6)) * math.sqrt(nr))), sharex=True,
                             sharey=True)  # make it fit a 3:2 ratio screen
        ax = ax.flatten()
        for i in range(0, nr):
            ax[i].scatter(spreads[:, 5 * (i + j)], ages, s=1, marker=",")
            ax[i].set_title(str(5 * (i + j)))
        plt.savefig(get_path("images/age_comp_" + str(j)))
        plt.gcf().clear()


def smooth_test():
    spreads = load_feature(spread)
    features = []
    for spreaded in spreads:
        smoothed = smooth(spreaded)
        features.append(smoothed)
    features = np.array(features)
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ages = load_ages()
    for i in range(len(features[0])):
        ax.scatter([i] * len(ages), ages, features[:, i])

    ax.set_xlabel('comp')
    ax.set_ylabel('age')
    ax.set_zlabel('intensity')

    plt.show()


def PCA_test():
    ages = load_ages()
    pc = load_feature(PCA_transform)
    print pc
    print ages
    plt.scatter(pc[:, 2], ages)
    plt.show()


def smooth_test2():
    spreads = load_feature(spread)
    features = []
    for spreaded in spreads:
        smoothed = smooth(spreaded)
        features.append(smoothed)
    features = np.array(features)
    nr = 20
    ages = load_ages()
    plt.locator_params(axis='x', nbins=5)
    for j in range(0, 400, nr):
        _, ax = plt.subplots(int(math.ceil((2 / math.sqrt(6)) * math.sqrt(nr))),
                             int(math.ceil((3 / math.sqrt(6)) * math.sqrt(nr))), sharex=True,
                             sharey=True)  # make it fit a 3:2 ratio screen
        ax = ax.flatten()
        for i in range(0, nr):
            ax[i].scatter(features[:, 5 * (i + j)], ages, s=1, marker=",")
            ax[i].set_title(str(5 * (i + j)))
        plt.savefig(get_path("images/age_comp_smooth" + str(j)))
        plt.gcf().clear()


def mask_similarity_test():
    images = load_images(0, 10, False, 1)
    for i in range(len(images) - 1):
        sum_of_difference = np.sum(find_border_mask(images[i]) - find_border_mask(images[i + 1]))
        print sum_of_difference


def plot_half_mask_test():
    images = load_images(0, 1, False, 2)
    border_mask = find_border_mask(images[0])
    mask = border_mask - get_shaved_mask(border_mask, 12)
    heigth = 45
    mask = mask[:, :, -heigth:]
    print np.unique(mask)
    print np.sum(mask)
    xs = []
    ys = []
    zs = []
    for x in range(len(mask) / 2):
        for y in range(len(mask[0])):
            for z in range(len(mask[0][0])):
                if mask[x][y][z][0] == 1:
                    xs.append(x)
                    ys.append(y)
                    zs.append(z)
    xs = np.array(xs)
    ys = np.array(ys)
    zs = np.array(zs)
    fig = plt.figure()
    ax = fig.gca(projection='3d')
    ax.set_aspect('equal')
    ax.scatter(xs, ys, zs)
    max_range = np.array([xs.max() - xs.min(), ys.max() - ys.min(), zs.max() - zs.min()]).max() / 2.0
    mid_x = (xs.max() + xs.min()) * 0.5
    mid_y = (ys.max() + ys.min()) * 0.5
    mid_z = (zs.max() + zs.min()) * 0.5
    ax.set_xlim(mid_x - max_range, mid_x + max_range)
    ax.set_ylim(mid_y - max_range, mid_y + max_range)
    ax.set_zlim(mid_z - max_range, mid_z + max_range)
    plt.show()


def plot_slices():
    ages = load_ages()
    for i in range(1, 5):
        rel_path = "input/set_" + "train" + "/" + "train" + "_" + str(i) + ".nii"
        image = nib.load(get_path(rel_path))  # load the input
        plot = nilplt.plot_anat(image, title=("healthy" if ages[i - 1] == 1 else "ill"))
        nilplt.show()


def save_slices():
    ages = load_ages()
    for i in range(51, 150):
        rel_path = "input/set_" + "train" + "/" + "train" + "_" + str(i) + ".nii"
        image = nib.load(get_path(rel_path))  # load the input
        nilplt.plot_anat(image, output_file=get_path(
            "images/anatomicplot" + str(i) + "_" + ("healthy" if ages[i - 1] == 1 else "ill")),
                         title=("healthy" if ages[i - 1] == 1 else "ill"))


def plot_derivatives():
    images = load_images(0, 10, False, 5)
    derivs = derivatives(images)
    ages = load_ages()
    _, ax = plt.subplots(2, 5, sharex=True, sharey=True)
    ax = ax.flatten()
    for i in range(0, len(images)):
        ax[i].scatter(range(0, len(derivs[i])), derivs[i], s=1, marker=",")
        ax[i].set_title("healthy" if ages[i] == 1 else "ill")
    plt.show()


def plot_sobel():
    images = load_images(0, 1, False, 2)
    image = images[0]
    print image.shape
    sx = sp.ndimage.filters.sobel(image, axis=0)
    print sx.max()
    sy = sp.ndimage.filters.sobel(image, axis=1)
    print sy.max()
    sz = sp.ndimage.filters.sobel(image, axis=2)
    print sz.max()
    sob = np.sqrt(sx * sx + sy * sy + sz * sz)
    sob = sob[:, :, :, 0]  # unpack
    print sob.shape
    sob = sob[40]
    plt.imshow(sob, cmap="gray")
    plt.show()


def plot_spread_sobel():
    images = load_images(0, 10, False, 2)
    spreads = feature_on_cerebrum(images, feature_function=sobel)
    ages = load_ages()
    _, ax = plt.subplots(2, 5, sharex=True, sharey=True)
    ax = ax.flatten()
    for i in range(0, len(images)):
        ax[i].scatter(range(0, len(spreads[i])), spreads[i], s=1, marker=",")
        ax[i].set_title("healthy" if ages[i] == 1 else "ill")
    plt.show()


def plot_median_sobel():
    images = load_images(0, 50, False, 2)
    mean = sobel(images, lambda x: [[np.mean(xi)] for xi in x])
    vars = sobel(images, variance_feature)
    ages = load_ages()
    for i in range(0, len(images)):
        plt.scatter(median[i][0], variance[i][0], s=1, marker=",", color="blue" if ages[i] == 1 else "red")
    plt.show()


def test_max_sobel():
    images = load_images(0, 50, False, 2)
    sobels = sobel(images, lambda x: x)
    ages = load_ages()
    for i in range(0, len(images)):
        print sobels[i].max()
        print ages[i]
