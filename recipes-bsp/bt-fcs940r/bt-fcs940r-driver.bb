SUMMARY = "Example of how to build an external Linux kernel module"
LICENSE = "CLOSED"

inherit module systemd

SRC_URI = "file://driver \
           file://FW \
           file://hci-uart-load.service \
           file://rtk_bluetooth.service \
           file://rtk_bt_monitor.service \
           file://fcs940r_bt_monitor.sh"

SRCREV ?= "${AUTOREV}"

S = "${WORKDIR}/driver/uart/bluetooth_uart_driver"
S1 = "${WORKDIR}/driver/uart/rtk_hciattach"

EXTRA_OEMAKE += 'KDIR:=/mnt/disk2/nxp/boundarydeviceEx/build/tmp/work-shared/nitrogen8mp/kernel-source'
EXTRA_OEMAKE += 'KVER="${KERNEL_VERSION}"'

TARGET_CC_ARCH += "${LDFLAGS}"
INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"

INSANE_SKIP:${PN} += "already-stripped"  
INHIBIT_PACKAGE_STRIP = "1"             
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

PARALLEL_MAKE = "-j 8"

SYSTEMD_SERVICE:${PN} = "hci-uart-load.service"
# SYSTEMD_SERVICE:${PN} += "rtk_bluetooth.service"
# SYSTEMD_SERVICE:${PN} += "rtk_bt_monitor.service"

KERNEL_MODULES_META_PKGDIR = "${nonarch_base_libdir}/modules/${KERNEL_VERSION}"
FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

do_compile() {

    cd ${S}
    oe_runmake

    cd ${S1}
    oe_runmake
}

do_install () {
    install -d ${D}${KERNEL_MODULES_META_PKGDIR}/kernel/drivers/bluetooth
    install -m 0644 ${S}/hci_uart.ko ${D}${KERNEL_MODULES_META_PKGDIR}/kernel/drivers/bluetooth/

    install -d ${D}${sbindir}
    install -m 0755 ${S1}/rtk_hciattach ${D}${sbindir}

    install -d ${D}${nonarch_base_libdir}/firmware/rtlbt
    install ${WORKDIR}/FW/rtl8723d_config ${D}${nonarch_base_libdir}/firmware/rtlbt
    install ${WORKDIR}/FW/rtl8723d_fw ${D}${nonarch_base_libdir}/firmware/rtlbt

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/hci-uart-load.service ${D}${systemd_system_unitdir}
    # install -m 0644 ${WORKDIR}/rtk_bluetooth.service ${D}${systemd_system_unitdir}
    # install -m 0644 ${WORKDIR}/rtk_bt_monitor.service ${D}${systemd_system_unitdir}

    # install -d ${D}${datadir}/fcs940r_bt/
	# install -m 755 ${WORKDIR}/fcs940r_bt_monitor.sh ${D}${datadir}/fcs940r_bt/
}
# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

# RPROVIDES_${PN} += "kernel-module-hci-uart-${KERNEL_VERSION}"

FILES:${PN} += "${sbindir}"
# FILES:${PN} += "${KERNEL_MODULES_META_PKGDIR}/kernel/drivers/bluetooth/hci_uart.ko"
FILES:${PN} += "${nonarch_base_libdir}/firmware/rtlbt"
FILES:${PN} += "${systemd_system_unitdir}/hci-uart-load.service"
# FILES:${PN} += "${systemd_system_unitdir}/rtk_bluetooth.service"
# FILES:${PN} += "${systemd_system_unitdir}/rtk_bt_monitor.service"
# FILES:${PN} += "/usr/share/fcs940r_bt/*"

SYSTEMD_AUTO_ENABLE = "enable"
