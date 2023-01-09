LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${TOPDIR}/../trustme/build/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = "file://install_sbkeys.sh"

TEST_CERT_DIR = "${TOPDIR}/test_certificates"
SB_KEYS_DIR = "${D}${datadir}/sbkeys"

DEPENDS = "pki-native"
RDEPENDS:${PN} = "efitools"

do_install() {
	install -d ${SB_KEYS_DIR}
	cp --dereference ${TEST_CERT_DIR}/DB.esl ${SB_KEYS_DIR}
	cp --dereference ${TEST_CERT_DIR}/KEK.esl ${SB_KEYS_DIR}
	cp --dereference ${TEST_CERT_DIR}/PK.auth ${SB_KEYS_DIR}

	install -d ${D}${bindir}
	install -m 755 ${WORKDIR}/install_sbkeys.sh ${D}${bindir}
}

FILES:${PN} += " /usr/*"
