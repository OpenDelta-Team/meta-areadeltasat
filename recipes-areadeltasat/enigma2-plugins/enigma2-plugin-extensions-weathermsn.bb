SUMMARY = "Weather MSN - Weather forecast for five days"
DESCRIPTION = "Weather forecast for 5 days"
MAINTAINER = "Sirius"
HOMEPAGE = "www.gisclub.tv"
require conf/license/license-gplv2.inc

inherit gitpkgv allarch

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://github.com/OpenDeltaE2-Plugins/enigma2-plugins-weathermsn.git;protocol=https;branch=master"

SRCREV = "${AUTOREV}"

FILES_${PN} = "${libdir}/enigma2/"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}${libdir}
	cp -r --preserve=mode,links ${S}${libdir}/* ${D}${libdir}/
	chmod -R a+rX ${D}${libdir}/enigma2/
}
