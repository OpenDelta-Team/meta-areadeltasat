DESCRIPTION = "Skins AREADELTSAT FHD"
MAINTAINER = "areadelta"
LICENSE = "CLOSED"

inherit gittag allarch python3-compileall

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

SRC_URI="git://github.com/OpenDeltaE2/skin-areadeltasat-fhd.git;protocol=https;branch=master"

FILES_${PN} = "${prefix}/"

S = "${WORKDIR}/git"

do_compile() {
	python3 -O -m compileall ${S}${libdir}/enigma2/python/Components/
}

do_install() {
	install -d ${D}${prefix}
	cp -r ${S}${prefix}/* ${D}${prefix}/
}
