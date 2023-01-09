SUMMARY = "efitools"
DESCRIPTION = "EFI tools to generate an deploy platform keys"
HOMEPAGE = "https://git.kernel.org/pub/scm/linux/kernel/git/jejb/efitools.git/"
SECTION = "console/tools"
LICENSE = "GPL-2.0-only"

LIC_FILES_CHKSUM = "file://COPYING;md5=e28f66b16cb46be47b20a4cdfe6e99a1"

SRC_URI = "https://git.kernel.org/pub/scm/linux/kernel/git/jejb/efitools.git/snapshot/efitools_${PV}.tar.gz"
SRC_URI += "file://0001-lib-console-compatibly-fix-to-upstream-change-of-gnu.patch"

S = "${WORKDIR}/efitools_${PV}"
