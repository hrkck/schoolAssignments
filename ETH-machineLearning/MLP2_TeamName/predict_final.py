# Note: this file is normally not in our directory structure, we just put it here for the submission purposes only.
# The code assumes the /data folder (as described in the assignment) in the same directory as this file.
# After executing, the csv file with the test ages will be available in the folder as "final_sub.csv"

import numpy as np

from src.app.loaders import load_all_images
from src.app.core import execute as execute

# load data
images = np.concatenate((load_all_images(False, 2), load_all_images(True, 2)))

# execute machine learning
execute(images)
