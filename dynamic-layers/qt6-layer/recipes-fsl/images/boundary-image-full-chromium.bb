# This image extends boundary-image-full with chromium added

require boundary-image-full.bb

CORE_IMAGE_EXTRA_INSTALL += " chromium-ozone-wayland"
