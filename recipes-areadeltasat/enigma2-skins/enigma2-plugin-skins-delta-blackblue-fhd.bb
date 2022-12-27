DESCRIPTION = "Skins delta black fhd"
MAINTAINER = "areadelta"
LICENSE = "CLOSED"

inherit gitpkgv allarch

PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"

SRC_URI="git://github.com/OpenDeltaE2/skins-delta-blackblue-fhd.git;protocol=https;branch=master"

FILES_${PN} = "${datadir}/enigma2/"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}${datadir}
	cp -r --preserve=mode,links ${S}${datadir}/* ${D}${datadir}/
	chmod -R a+rX ${D}${datadir}/enigma2/
}
