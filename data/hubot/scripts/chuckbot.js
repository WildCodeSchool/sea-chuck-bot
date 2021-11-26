module.exports = (chuckbot) => {
    chuckbot.respond(/(whataboutchuck)/gi, (res) => {
            //wrap the HTTP get call as a Promise
            user = process.env.CHUCKBOT_USERNAME
            pass = process.env.CHUCKBOT_PASSWORD
            auth = 'Basic ' + new Buffer(user + ':' + pass).toString('base64')
            new Promise((resolve, reject) =>
                chuckbot.http(process.env.CHUCKBOT_URL)
                    .headers(Authorization: auth, Accept: 'application/json')
                    .get()((err, response, body) =>
                    err ? reject(err) : resolve(JSON.parse(body).jokeText)
                )
            )
            //reply joke
            .then(body => res.reply(body))
    })
}