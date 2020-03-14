const Discord = new (require('./modules/DiscordClient'))();
const ConsoleCmds = new (require('./modules/ConsoleCommands'))();
const TwitchApi = require('./modules/TwitchApi');

setInterval(TwitchApi.statusLive, 10000, Discord);
