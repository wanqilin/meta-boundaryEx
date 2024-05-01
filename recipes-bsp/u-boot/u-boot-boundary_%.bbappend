FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRCREV = "9ddfa77a79f860c1a3f580402c0bc08ca2382001"

SRCBRANCH:nitrogen93 = "boundary-lf_v2023.04"
SRCREV:nitrogen93 = "73dcd1c420337fb86b1cfa480b82c9fe5bf620a7"
LIC_FILES_CHKSUM:nitrogen93 = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"
PV:nitrogen93 = "v2023.04+git${SRCPV}"

COMPATIBLE_MACHINE = "(nitrogen6x-lite|nitrogen6x|nitrogen6sx|nitrogen7|nitrogen8m|nitrogen8mm|nitrogen8mn|nitrogen8mp|nitrogen8ulp|nitrogen93)"
