# This image extends boundary-image-multimedia with chromium added

require recipes-boundary/images/boundary-image-multimedia.bb

CORE_IMAGE_EXTRA_INSTALL += "chromium-ozone-wayland "
