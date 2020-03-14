const config = require('../.env.json');
const http = require('https');
const Discord = require('discord.js');

let isStreaming = false;

class TwitchApi {

    static statusLive(discordClient) {
        const tmpIsStreaming = isStreaming;
        getTwitchData(function (data) {
            if (data === null) return;
            if (isStreaming !== tmpIsStreaming) {
                if (isStreaming === true) {
                    const message = new Discord.MessageEmbed()
                        .setAuthor("FunixGaming", data.profile_picture)
                        .setColor("#6441a5")
                        .setTitle("[FunixLive] - FunixGaming est en live !")
                        .setURL("https://twitch.tv/funixgaming")
                        .setThumbnail(data.game_thumbnail)
                        .addField("Titre :", data.title)
                        .addField("Jeu :", data.game_name)
                        .addField("Lien :", "https://twitch.tv/funixgaming")
                        .setFooter("FunixLive - Notification de live", data.profile_picture);
                    discordClient.sendMessageChannel(config.defaults.channelsId.twitch, "FunixGaming est en live sur Twitch ! @here");
                    discordClient.sendMessageChannel(config.defaults.channelsId.twitch, message);
                } else {
                    const message = new Discord.MessageEmbed()
                        .setTitle("Fin du live !")
                        .setDescription("Pensez à follow FunixGaming sur les réseaux !")
                        .addField("Twitter :", "https://twitter.com/funixgaming")
                        .addField("Instagram :", "https://instagram.com/funixgaming_")
                        .setURL("https://twitter.com/funixgaming")
                        .setColor("#6441a5")
                        .setThumbnail(data.profile_picture)
                        .setFooter("FunixLive - Notification de live", data.profile_picture);
                    discordClient.sendMessageChannel(config.defaults.channelsId.twitch, message);
                }
            }
        });
    }

    static isStreaming() { return isStreaming; }
}

function getTwitchData(cb) {
    let dataTwitch = {
        title: null,
        profile_picture: null,
        game_name: null,
        game_thumbnail: null
    };
    let gameid = 0;
    const optionsLive = {
        host: 'api.twitch.tv',
        port: '443',
        path: '/helix/streams?user_login=' + config.api.twitch.twitchChannel,
        method: 'GET',
        headers: {
            'Client-ID': config.api.twitch.twitchKey
        }
    };
    dataTwitch.profile_picture = "https://funixgaming.fr/img/funixLogo.png";
    const res = http.request(optionsLive, function (fetch) {
        fetch.setEncoding('utf8');
        fetch.on('data', function (data) {
            if (data.length > 0 && isStreaming) return;
            if (data.length > 0 && !isStreaming) {
                data = JSON.parse(data);
                isStreaming = true;
                dataTwitch.title = data.data[0].title;
                gameid = data.data[0].game_id;
                const optionsGame = {
                    host: 'api.twitch.tv',
                    port: '443',
                    path: '/helix/games?id=' + gameid,
                    method: 'GET',
                    headers: {
                        'Client-ID': config.api.twitch.twitchKey
                    }
                };
                const res = http.request(optionsGame, function (fetch) {
                    fetch.setEncoding('utf8');
                    fetch.on('data', function (data) {
                        data = JSON.parse(data);
                        dataTwitch.game_name = data.data[0].name;
                        let gamePic = data.data[0].box_art_url;
                        gamePic = gamePic.replace("{width}", "600");
                        gamePic = gamePic.replace("{height}", "900");
                        dataTwitch.game_thumbnail = gamePic;
                        cb(dataTwitch);
                    });
                });
                res.on('error', function (err) {
                    console.error("Error while getting twitch api: " + err.message);
                    isStreaming = false;
                    cb(null);
                });
                res.end();

            }
            if (data.length < 0 && isStreaming) {
                isStreaming = false;
            }
        });
    });

    res.on('error', function (err) {
        console.error("Error while getting twitch api: " + err.message);
        isStreaming = false;
        cb(null);
    });

    res.end();
}

module.exports = TwitchApi;