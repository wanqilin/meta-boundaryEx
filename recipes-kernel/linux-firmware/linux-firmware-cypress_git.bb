SUMMARY = "WiFi and BT firmware files for Cypress based modules such as the LWB5+"
SECTION = "kernel"

LICENSE = "linux-firmware-cypress"

LIC_FILES_CHKSUM = "\
	file://LICENSE;md5=53d3628b28a0bc3caea61587feade5f9 \
"

SRCREV = "449919baa69f5ca60f9234f275bb5e329ccedeaf"

SRC_URI = "git://github.com/boundarydevices/cypress-firmware.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH = "lwb5plus-sdio-sa-firmware-10.54.0.13"

S = "${WORKDIR}/git"

inherit allarch

CLEANBROKEN = "1"

do_compile() {
	:
}

do_install() {
	DESTDIR=${D} make install
}

FILES:${PN} += "/lib/firmware/* /lib/firmware/*/*"

RDEPENDS_${PN} += "crda"