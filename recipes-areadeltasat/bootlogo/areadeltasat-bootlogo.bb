DESCRIPTION = "AREADELTASAT bootlogo"
SECTION = "base"
PRIORITY = "required"
MAINTAINER = "AREADELTASAT team"
LICENSE = "CC-BY-NC-ND-4.0"
LIC_FILES_CHKSUM = "file://${AREADELTASAT_BASE}/meta-areadeltasat/licenses/CC-BY-NC-ND-4.0;md5=8009795292660aa9c8da059e5b1581c1"

RDEPENDS:${PN} += "showiframe"

PV = "Elite"

S = "${WORKDIR}"

INITSCRIPT_NAME = "bootlogo"
INITSCRIPT_PARAMS = "start 21 S ."

inherit update-rc.d

SRC_URI = " \
	file://bootlogo.mvi \
	file://switchoff.mvi \
    file://bootlogo_wait.mvi \
	file://bootlogo.sh \
	file://logo-white-square.png"

MVI = "bootlogo.mvi"
MVISYMLINKS = "bootlogo_wait backdrop"

PNG = "logo-white-square.png"

do_install() {
	install -d ${D}/boot
	install -d ${D}${datadir}
	for i in ${MVI}; do
		install ${S}/$i ${D}${datadir}
		ln -sf ${datadir}/$i ${D}/boot/$i
	done
	for i in ${MVISYMLINKS}; do
		ln -sf /boot/bootlogo.mvi ${D}/boot/$i.mvi
		ln -sf ${datadir}/bootlogo.mvi ${D}${datadir}/$i.mvi;
	done
	install -d ${D}${datadir}/logo
	for i in ${PNG}; do
		install ${S}/$i ${D}${datadir}/logo
	done
	install -d ${D}/${sysconfdir}/init.d
	install -m 0755 ${S}/bootlogo.sh ${D}/${sysconfdir}/init.d/bootlogo
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES:${PN} = "/boot ${datadir} ${sysconfdir}/init.d"
