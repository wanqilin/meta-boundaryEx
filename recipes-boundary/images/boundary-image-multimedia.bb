# This image extends imx-image-multimedia with additional
# Boundary Devices packages

require recipes-fsl/images/imx-image-multimedia.bb

IMAGE_INSTALL_WIFI_BT ?= "${IMAGE_INSTALL_WIFI_BT_PKGS}"
IMAGE_INSTALL_WIFI_BT_PKGS = " \
    bdsdmac-firmware \
    if573-sdio-firmware \
    lwb5plus-sdio-sa-firmware \
    kernel-module-bdsdmac-backports \
"

IMX_GPU_VIV_DEMOS ?= "imx-gpu-viv-demos"
# imx-gpu-viv-demos are not compatible with i.MX7 and i.MX9
IMX_GPU_VIV_DEMOS:mx7-nxp-bsp = ""
IMX_GPU_VIV_DEMOS:mx93-nxp-bsp = ""

CORE_IMAGE_EXTRA_INSTALL += " \
	can-utils \
	e2fsprogs \
	evtest \
	fw-env-rules \
	i2c-tools \
	iperf3 \
	iproute2 \
	libdrm-tests \
	memtester \
	minicom \
	mmc-utils \
	modemmanager \
	networkmanager \
	networkmanager-nmcli \
	openssh \
	packagegroup-fsl-isp \
	packagegroup-fsl-opencv-imx \
	packagegroup-imx-ml \
	packagegroup-tools-bluetooth \
	pciutils \
	psplash \
	screen \
	spitools \
	strace \
	tslib-tests tslib-calibrate \
	u-boot-boundary-env \
	u-boot-fw-utils \
	udev-rules-imx \
	v4l-utils \
	wireless-regdb-static \
	${IMAGE_INSTALL_WIFI_BT} \
	${IMX_GPU_VIV_DEMOS} \
"
