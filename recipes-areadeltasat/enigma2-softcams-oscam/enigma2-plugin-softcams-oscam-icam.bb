require OScam-icam-version.inc
SUMMARY = "Softcams OScam-icam"
LICENSE = "CLOSED"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"

PV = "183D"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN}_append = " already-stripped"

S = "${WORKDIR}"

CAMNAME = "oscam-icam"

SRC_URI += " \
	file://oscam-icam \
	file://oscam.conf \
    file://oscam.server \
    file://oscam.srvid2 \
    file://oscam.user \
    file://oscam.provid \
    file://oscam.dvbapi \
	file://softcam.oscam-icam"

CONFFILES = "${sysconfdir}/tuxbox/config/oscan-icam/oscam.conf ${sysconfdir}/tuxbox/config/oscam-icam/oscam.server ${sysconfdir}/tuxbox/config/oscam-icam/oscam.srvid2 ${sysconfdir}/tuxbox/config/oscam-icam/oscam.user ${sysconfdir}/tuxbox/config/oscam-icam/oscam.dvbapi ${sysconfdir}/tuxbox/config/oscam-icam/oscam.provid"

INITSCRIPT_NAME = "softcam"
INITSCRIPT_PARAMS = "defaults 50"
inherit update-rc.d

FILES_${PN} = "${bindir}/oscam-icam ${sysconfdir}/tuxbox/config/oscam-icam/* ${sysconfdir}"

do_install() {
	install -d ${D}${sysconfdir}/tuxbox/config/oscam-icam
	install -m 0644 ${WORKDIR}/oscam.* ${D}${sysconfdir}/tuxbox/config/oscam-icam/
	install -d ${D}${bindir}
	install -m 0755 ${B}/oscam-icam ${D}${bindir}
	install -d ${D}${sysconfdir}/init.d
    install -m 755 ${S}/softcam.oscam-icam ${D}${sysconfdir}/init.d/softcam.oscam-icam
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
