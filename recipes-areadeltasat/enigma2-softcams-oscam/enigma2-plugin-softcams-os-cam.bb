require OScam-version.inc
SUMMARY = "Softcams oscam mod by loda"
LICENSE = "CLOSED"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"

PV = "183D"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN}_append = " already-stripped"

S = "${WORKDIR}"

CAMNAME = "oscam"

SRC_URI += " \
	file://oscam \
	file://oscam.conf \
    file://oscam.server \
    file://oscam.srvid2 \
    file://oscam.user \
    file://oscam.provid \
    file://oscam.dvbapi \
	file://softcam.oscam"

CONFFILES = "${sysconfdir}/tuxbox/config/os-cam/oscam.conf ${sysconfdir}/tuxbox/config/os-cam/oscam.server ${sysconfdir}/tuxbox/config/os-cam/oscam.srvid2 ${sysconfdir}/tuxbox/config/os-cam/oscam.user ${sysconfdir}/tuxbox/config/os-cam/oscam.dvbapi ${sysconfdir}/tuxbox/config/os-cam/oscam.provid"

INITSCRIPT_NAME = "softcam"
INITSCRIPT_PARAMS = "defaults 50"
inherit update-rc.d

FILES_${PN} = "${bindir}/oscam ${sysconfdir}/tuxbox/config/os-cam/* ${sysconfdir}"

do_install() {
	install -d ${D}${sysconfdir}/tuxbox/config/os-cam
	install -m 0644 ${WORKDIR}/oscam.* ${D}${sysconfdir}/tuxbox/config/os-cam/
	install -d ${D}${bindir}
	install -m 0755 ${B}/oscam ${D}${bindir}
	install -d ${D}${sysconfdir}/init.d
    install -m 755 ${S}/softcam.oscam ${D}${sysconfdir}/init.d/softcam.oscam
	install -m 755 ${S}/softcam.None ${D}${sysconfdir}/init.d/softcam.None
}

do_compile_append() {
	echo "# Placeholder for no cam" > ${S}/softcam.None
}

pkg_postinst_${PN} () {
	if [ ! -e "$D${sysconfdir}/init.d/softcam" ]
	then
		ln -s softcam.oscam $D${sysconfdir}/init.d/softcam
	fi
}
