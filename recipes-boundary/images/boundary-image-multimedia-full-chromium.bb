# This image extends boundary-image-multimedia-full with chromium added

require recipes-boundary/images/boundary-image-multimedia-full.bb

CORE_IMAGE_EXTRA_INSTALL += "chromium-ozone-wayland \
                            weston weston-init \
                            python3 \
                            apache2 \
                            apr \
                            apr-util \
                            openssl \
                            packagegroup-core-buildessential \
                            glibc-dev \
                            glibc-staticdev \
                            kernel-devsrc \
                            "
