#!/bin/bash

set -euo pipefail

LOG_TAG="bt-monitor"

while true; do
  if ! hciconfig hci0 | grep -q RUNNING; then
    logger -t $LOG_TAG "Bluetooth interface down! Reinitializing..."
    if ! systemctl restart rtk-bluetooth.service; then
      logger -t $LOG_TAG "Failed to restart service! Error code: $?"
      exit 1
    fi
  fi
  sleep 30
done
