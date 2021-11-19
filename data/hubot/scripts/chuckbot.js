module.exports = (chuckbot) => {
    chuckbot.respond(/(whataboutchuck)/gi, (res) => {
                //wrap the HTTP get call as a Promise
            new Promise((resolve, reject) =>
                chuckbot.http("http://chuckjokeservice:8080/api/jokes/random").get()((err, response, body) =>
                    err ? reject(err) : resolve(JSON.parse(body).jokeText)
                )
            )
            //reply joke
            .then(body => res.reply(body))
    })
}