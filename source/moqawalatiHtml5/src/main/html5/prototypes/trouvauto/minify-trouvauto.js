var fs = require('fs');
var inputFile = 'i.htm';
var outputFile = 'i.m.htm';
fs.readFile(inputFile, 'utf8', function (err, data) {
    if (err) {
        return console.log(err);
    }

    var minify = require('html-minifier').minify;
    var minHTML = minify(data, {
        collapseWhitespace: true,
        keepClosingSlash: true,
        minifyCSS: true,
        minifyJS: true,
        removeAttributeQuotes: true,
        removeComments: true
    });

    var symbolsToReplace = minHTML.match(/\b_[a-z|A-Z|0-9|_]*_\b/g);
    if (symbolsToReplace) {
        symbolsToReplace = getUniqueSymbolsAndSortByRelevance(symbolsToReplace);
        var i = 0;
        symbolsToReplace.forEach(function (symbolObj) {
            var minSymbol = getMinSymbolForIndex(i);
            console.log('symbol=' + symbolObj.symbol + ', count=' + symbolObj.count + ", min=" + minSymbol);
            var regex = new RegExp('\\b' + symbolObj.symbol + '\\b', 'g');
            minHTML = minHTML.replace(regex, minSymbol);
            i++;
        });
    }
    fs.writeFile(outputFile, minHTML, 'utf8', function (err) {
        if (err) return console.log(err);
    });
});

// private
function getMinSymbolForIndex(idx) {
    var addUnderscore = false;
    if (idx > 61) {
        return '_' + (idx - 52);
    }
    var asciiCode = 65 + idx; // A=65, Z=90
    if (idx > 35) {
        addUnderscore = true;
        asciiCode = 61 + idx; // a=97, z=122
    }
    else if (idx > 25) {
        addUnderscore = true;
        asciiCode = 22 + idx; // 0=48, 9=57
    }
    return (addUnderscore ? '_' : '') + String.fromCharCode(asciiCode);
}

// private
// return array of {symbol:String, count:Number}
// Sorted descending by count
function getUniqueSymbolsAndSortByRelevance(symbols) {
    var dico = {};
    symbols.forEach(function (symbol) {
        if (!dico[symbol]) {
            dico[symbol] = {
                symbol: symbol,
                count: 0
            };
        }
        dico[symbol].count++;
    });
    var result = Object.keys(dico).map(function (key) {
        return dico[key]
    });
    result.sort(function (a, b) {
        var result = b.count - a.count;
        if (result == 0)
            result = b.symbol.length - a.symbol.length;
        return result;
    });
    return result;
}