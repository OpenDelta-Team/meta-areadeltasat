SUMMARY = "Vhannibal Setting mod by readelta"
MAINTAINER = "AREADELTASAT TEAM"
LICENSE = "CLOSED"

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

SRC_URI = "git://github.com/OpenDeltaE2/Vhannibal-Setting-mod-by-areadelta.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

inherit allarch gitpkgv

FILES_${PN} = "${sysconfdir}/enigma2/"

do_install () {
	install -d ${D}${sysconfdir}/enigma2
	cp -r ${S}/${SETTINGS_FILENAME}/* ${D}${sysconfdir}/enigma2
	rm -rf ${D}${sysconfdir}/enigma2/satellites.xml*
}
