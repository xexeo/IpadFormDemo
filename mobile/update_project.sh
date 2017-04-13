#!/bin/bash
sudo npm update -g cordova
cordova platform remove android
cordova platform remove ios
cordova platform add ios

cordova plugin remove cordova-plugin-dialogs
cordova plugin add cordova-plugin-dialogs

cordova plugin remove cordova-plugin-device
cordova plugin add cordova-plugin-device

cordova plugin remove cordova-plugin-file
cordova plugin add cordova-plugin-file

cordova plugin remove io.phasr.cordova.plugin.itunesfilesharing
cordova plugin add cordova-plugin-itunesfilesharing

cordova plugin remove cordova-plugin-splashscreen
cordova plugin add cordova-plugin-splashscreen

cordova plugin remove cordova-plugin-statusbar
cordova plugin add cordova-plugin-statusbar

cordova plugin remove cordova-sqlite-storage
cordova plugin add cordova-sqlite-storage

cordova plugin remove cordova-plugin-battery-status
cordova plugin add cordova-plugin-battery-status
