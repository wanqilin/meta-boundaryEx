#ifneq ($(KERNELRELEASE),)
	obj-m		:= hci_uart.o
	hci_uart-y	:= hci_ldisc.o hci_h4.o hci_rtk_h5.o rtk_coex.o
	#EXTRA_CFLAGS += -DDEBUG

#else
	PWD := $(shell pwd)
	KVER := ${KERNEL_VERSION}
	# KDIR := /lib/modules/$(KVER)/build
	KDIR := /mnt/disk2/nxp/boundarydeviceEx/build/tmp/work-shared/nitrogen8mp/kernel-source #yocto imx.8 support

all:
	echo $(KVER)
	$(MAKE) -C $(KDIR) M=$(PWD) modules

clean:
	rm -rf *.o *.mod.c *.mod.o *.ko *.symvers *.order *.a

#endif
