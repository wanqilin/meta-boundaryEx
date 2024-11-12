SUMMARY = "Example of how to build an external Linux kernel module"
LICENSE = "CLOSED"

inherit module

SRC_URI = "file://code \
           file://wifi/txpower "

SRCREV = "${AUTOREV}"

S = "${WORKDIR}"
PARALLEL_MAKE = "-j 8"

INSANE_SKIP:${PN} += "installed-vs-shipped"
do_configure () {
bbnote skip do_configure
}
do_compile() {
oe_runmake -C ${WORKDIR}/code
}

do_install() {
	install -d ${D}${base_sbindir}
	install -d ${D}${nonarch_base_libdir}/modules
	install -d ${D}${nonarch_base_libdir}/modules/extra/
	install -d ${D}${nonarch_base_libdir}/firmware/
	install -m 755 ${WORKDIR}/code/*.ko ${D}${nonarch_base_libdir}/modules/extra/
	install -m 755 ${WORKDIR}/wifi/txpower/PHY_REG_PG.txt ${D}${nonarch_base_libdir}/firmware/
	install -m 755 ${WORKDIR}/wifi/txpower/TXPWR_LMT.txt ${D}${nonarch_base_libdir}/firmware/

}
# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
RPROVIDES_${PN} += "kernel-module-fcs940r-driver"

FILES:${PN} += "${nonarch_base_libdir}/firmware"