SRCREV = "0542fa2fda2ac04f70e513f1603f335f27976636"

SRCBRANCH:nitrogen93 = "boundary-imx_6.1.y"
SRCREV:nitrogen93 = "328b70866e9d2e629eca0276f02a715bd10718c9"

COMPATIBLE_MACHINE = "(nitrogen6x|nitrogen6x-lite|nitrogen6sx|nitrogen7|nitrogen8m|nitrogen8mm|nitrogen8mn|nitrogen8mp|nitrogen8ulp|nitrogen93)"

# In case of 8mp, kernel-module-isp-vvcam will build and cause the following error:
# The recipe linux-boundary is trying to install files into a shared area when those files already exist (kernel-module-imx219)
# So we need to remove config from kernel to avoid error.
EXTRA_OEMAKE:append:mx8mp-nxp-bsp = " CONFIG_VIDEO_IMX219=n"
