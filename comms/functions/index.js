const functions = require('firebase-functions');

const admin = require('firebase-admin');
admin.initializeApp()

// Create and Deploy Your First Cloud Functions
// https://firebase.google.com/docs/functions/write-firebase-functions

exports.predict = functions.https.onCall((request, response) => {
    const data = request.text;
    return {
        text: "hi from func"
    };
//  response.send("Hello from Firebase!");
});
