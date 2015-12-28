var fs = require('fs');
var fileName = 'i.m.htm';
fs.readFile(fileName, 'utf8', function (err,data) {
  if (err) {
    return console.log(err);
  }
  var result = data.replace(/_i18n_/g, '_1');

  fs.writeFile(fileName, result, 'utf8', function (err) {
    if (err) return console.log(err);
  });
});