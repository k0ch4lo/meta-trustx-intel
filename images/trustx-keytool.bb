inherit trustmekeytool

DECRIPTION = "KeyTool image"

DEPENDS= "efitools-native"

IMAGE_LINUGUAS = " "

LICENSE = "GPL-2.0-only"

IMAGE_FEATURES = ""

export IMAGE_BASENAME = "trustx-keytool"
IMAGE_FSTYPES = "trustmekeytool"
inherit image

IMAGE_ROOTFS_SIZE = "4096"
