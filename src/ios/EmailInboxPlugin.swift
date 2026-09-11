import Foundation
import UIKit

@objc(EmailInboxPlugin)
class EmailInboxPlugin: CDVPlugin {

    @objc(openEmailApp:)
    func openEmailApp(_ command: CDVInvokedUrlCommand) {
        guard let url = URL(string: "message:"), UIApplication.shared.canOpenURL(url) else {
            let pluginResult = CDVPluginResult(status: CDVCommandStatus_ERROR, messageAs: "NO_EMAIL_APP")
            self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
            return
        }

        UIApplication.shared.open(url, options: [:]) { success in
            let status: CDVCommandStatus = success ? CDVCommandStatus_OK : CDVCommandStatus_ERROR
            let pluginResult = CDVPluginResult(status: status)
            self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
        }
    }
}
