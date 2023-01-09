COMPATIBLE_MACHINE = "${MACHINE}"

SRC_URI += " \
    file://trustx-intel.cfg \
    file://qemu.cfg \
"

FILESEXTRAPATHS:prepend := "${THISDIR}/generic:"
