LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/cu-ecen-aeld/assignments-3-and-later-mishajoshi.git;protocol=https;branch=main"

PV = "1.0+git${SRCPV}"
SRCREV = "38e7171d152b8a55f0b3fceedfbff90a114055b6"

S = "${WORKDIR}/git/server"

# ✅ Use init.d instead of systemd
inherit update-rc.d

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "S99aesdsocket"
INITSCRIPT_PARAMS:${PN} = "defaults"

TARGET_LDFLAGS += "-lpthread"

FILES:${PN} += "${bindir}/aesdsocket"

do_configure () {
    :
}

do_compile () {
    oe_runmake
}

do_install () {
    # install binary
    install -d ${D}${bindir}
    install -m 0755 ${S}/aesdsocket ${D}${bindir}

    # install init script
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/S99aesdsocket ${D}${sysconfdir}/init.d
}
