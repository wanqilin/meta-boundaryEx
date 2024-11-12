SUMMARY = "Example of how to build an external Linux kernel module"
LICENSE = "CLOSED"

inherit module

SRC_URI = "file://driver \
           file://FW"

SRCREV ?= "${AUTOREV}"

S = "${WORKDIR}/driver/uart/bluetooth_uart_driver"
S1 = "${WORKDIR}/driver/uart/rtk_hciattach"

EXTRA_OEMAKE += 'KDIR:=${KBUILD_OUTPUT}'
EXTRA_OEMAKE += 'KVER="${KERNEL_VERSION}"'

TARGET_CC_ARCH += "${LDFLAGS}"
INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"
PARALLEL_MAKE = "-j 8"

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

do_compile() {
    cd ${S}
    oe_runmake

    cd ${S1}
    oe_runmake
}

do_install () {
    install -d ${D}${nonarch_base_libdir}/fcs940r_modules
    cp --no-preserve=ownership ${S}/hci_uart.ko ${D}${nonarch_base_libdir}/fcs940r_modules
    install -m 0644 ${S}/hci_uart.ko ${D}${nonarch_base_libdir}/fcs940r_modules/

    install -d ${D}${sbindir}
    install -m 0755 ${S1}/rtk_hciattach ${D}${sbindir}

    install -d ${D}${nonarch_base_libdir}/firmware/rtlbt
    install -m 755 ${WORKDIR}/FW/rtl8723d_config ${D}${nonarch_base_libdir}/firmware/rtlbt
    install -m 755 ${WORKDIR}/FW/rtl8723d_fw ${D}${nonarch_base_libdir}/firmware/rtlbt
}
# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

# RPROVIDES_${PN} += "kernel-module-hci-uart"

FILES:${PN} += "${sbindir}"
FILES:${PN} += "${nonarch_base_libdir}/fcs940r_modules/*"
FILES:${PN} += "${nonarch_base_libdir}/firmware/rtlbt"
