# Useful if you want to keep the data loaded in memory when making small changes. Data will also be kept memory when an
# error occurs in the code your executing.

import sys
import traceback

import numpy as np

import core
import experiments
import features
import loaders

# set to false if you want to do an experiment and NOT a submission
SUBMISSION = False
COMPRESSION = 5

# load data in memory
if SUBMISSION:
    images = np.concatenate((loaders.load_all_images(False, COMPRESSION), loaders.load_all_images(True, COMPRESSION)))
else:
    images = loaders.load_all_images(False, COMPRESSION)

while True:
    try:
        # execute your function
        if SUBMISSION:
            core.execute(images)
        else:
            experiments.execute(images)
    except:
        traceback.print_exc()
    print "Press enter to re-run the script, CTRL-C to exit"
    sys.stdin.readline()

    print "Re-running..."

    reload(features)
    reload(loaders)
    reload(experiments)
    reload(core)
