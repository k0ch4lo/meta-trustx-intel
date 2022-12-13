require efitools-common.inc

SRC_URI[md5sum] = "f2da7eb801f6965b19d61ca3341f9ecb"
SRC_URI[sha256sum] = "f4ece634a498bde73dc23d1aab1171a07b64718bd47f167240a7db4049e729b4"

inherit native
inherit perlnative

DEPENDS = "gnu-efi-native help2man-native sbsigntool-native libfile-slurp-perl-native"

EFIFILES = "\
	LockDown.efi \
	KeyTool.efi \
"

BINARIES = "\
	cert-to-efi-sig-list \
	sig-list-to-certs \
	sign-efi-sig-list \
	hash-to-efi-sig-list \
	cert-to-efi-hash-list \
"

LIBRARY_FLAGS = "\
	-nostdlib -shared -Bsymbolic \
	${STAGING_LIBDIR}/crt0-efi-${HOST_ARCH}.o \
	-L ${STAGING_LIBDIR} \
	-T ${STAGING_LIBDIR}/elf_${HOST_ARCH}_efi.lds \
"

#tmp/sysroots-components/x86_64/gnu-efi-native/usr/lib/crt0-efi-x86_64.o

INCLUDES = "\
	-I${S}/include \
	-I${STAGING_INCDIR} \
	-I${STAGING_INCDIR}/efi \
	-I${STAGING_INCDIR}/efi/${HOST_ARCH} \
	-I${STAGING_INCDIR}/efi/protocol \
"

COMPILE_CFLAGS += "-D_XOPEN_SOURCE"
COMPILE_CFLAGS += "-DEFI_WARN_UNKOWN_GLYPH=EFI_WARN_UNKNOWN_GLYPH"

# overwrite compiler and compiler flags in makefile
EXTRA_OEMAKE = "\
        'CC = ${CC} -L ${STAGING_LIBDIR}' \
        'LD = ${LD}' \
	'INCDIR = ${INCLUDES}' \
	'LDFLAGS = ${LIBRARY_FLAGS}' \
"

# Due to their build system structure, efitools do not recognize
# the shared libraries residing in recipe-sysroot-native. To give
# a hint to the dynmic loader, we manually set LD_LIBRARY_PATH.
export LD_LIBRARY_PATH = "${STAGING_LIBDIR}"

do_compile() {
	oe_runmake ${BINARIES}
	oe_runmake ${EFIFILES}
}

do_install () {
	install -d ${D}${bindir}
	for bin in ${BINARIES} ${EFIFILES}; do
		install ${S}/${bin} ${D}${bindir}
	done
}
