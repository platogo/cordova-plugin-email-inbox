var exec = require('cordova/exec');

function EmailInboxPlugin() { }

EmailInboxPlugin.prototype.openEmailApp = function () {
    return new Promise(function (resolve, reject) {
        exec(resolve, reject, 'EmailInboxPlugin', 'openEmailApp', []);
    })
}

module.exports = new EmailInboxPlugin();
