# connman conflicts with networkmanager, so these cannot be installed.
RDEPENDS:${PN}:remove = " connman-tools connman-tests connman-client"
