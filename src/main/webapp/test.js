/*
    MIME类型（Multipurpose Internet Mail Extensions）用来描述消息内容类型的标准，用来表示文档、文件或字节流的性质和格式
    MIME 消息能包含文本、图像、音频、视频以及其他应用程序专用的数据
    浏览器通常使用 MIME 类型（而不是文件扩展名）来确定如何处理URL，
    因此 We b服务器在响应头中添加正确的 MIME 类型非常重要。
    如果配置不正确，浏览器可能会无法解析文件内容，网站将无法正常工作，并且下载的文件也会被错误处理。
 */
//MIME 类型通用结构：
//
// type/subtype
// MIME 的组成结构非常简单，由类型与子类型两个字符串中间用 / 分隔而组成，不允许有空格。type 表示可以被分多个子类的独立类别，subtype 表示细分后的每个类型。
//
// MIME类型对大小写不敏感，但是传统写法都是小写。
//
// 两种主要的 MIME 类型在默认类型中扮演了重要的角色：
//
// text/plain 表示文本文件的默认值。
// application/octet-stream 表示所有其他情况的默认值。
// 常见的 MIME 类型
// 超文本标记语言文本 .html、.html：text/html
//
// 普通文本 .txt： text/plain
//
// RTF 文本 .rtf： application/rtf
//
// GIF 图形 .gif： image/gif
//
// JPEG 图形 .jpeg、.jpg： image/jpeg
//
// au 声音文件 .au： audio/basic
//
// MIDI 音乐文件 mid、.midi： audio/midi、audio/x-midi
//
// RealAudio 音乐文件 .ra、.ram： audio/x-pn-realaudio
//
// MPEG 文件 .mpg、.mpeg： video/mpeg
//
// AVI 文件 .avi： video/x-msvideo
//
// GZIP 文件 .gz： application/x-gzip
//
// TAR 文件 .tar： application/x-tar
/**
 * JSON 文件
 * JSON 文件的文件类型是 ".json"
 * JSON 文本的 MIME 类型是 "application/json"
 */
/*
有效的数据类型
在 JSON 中，值必须是以下数据类型之一：

字符串
数字
对象（JSON 对象）
数组
布尔
Null
JSON 的值不可以是以下数据类型之一：

函数
日期
undefined
 */
/**
 * JSON 的常规用途是同 web 服务器进行数据传输。
 *
 * 在从 web 服务器接收数据时，数据永远是字符串。
 *
 * 通过 JSON.parse() 解析数据，这些数据会成为 JavaScript 对象
 */
/*从服务器获取json字符串
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function()  {
    if (this.readyState == 4 && this.status == 200) {
        myObj =  JSON.parse(this.responseText);
        document.getElementById("demo").innerHTML  = myObj.name;
    }
};
xmlhttp.open("GET", "json_demo.txt", true);
xmlhttp.send();
 */
/**JSON.parse("json字符串")
 * json字符串中的每个“key-value”如果想被javaScript解析成期望正确的数据格式，则需要正确的书写格式
 * 并且javaScript解析json字符串时，只允许json格式的对象，以及数组形式的对象
 * 其他的“Function f()”以及“new Date("2000-11-21")”是不会被包装成函数对象或者日期对象
 * 所以，可以获取字符串，在new Date（json.date）;
 */
/**
 * 或者您可以已使用 JSON.parse() 函数的第二个参数，被称为 reviver。
 *
 * 这个 reviver 参数是函数，在返回值之前，它会检查每个属性。
 *
 * 实例
 * 将字符串转换为日期，使用 reviver 函数：
 *
 * var text =  '{ "name":"Bill Gates", "birth":"1955-10-28", "city":"Seattle"}';
 * var obj = JSON.parse(text, function (key, value) {
 *     if  (key == "birth") {
 *         return new Date(value);
 *     } else {
 *          return value;
 *    }});
 *
 * document.getElementById("demo").innerHTML = obj.name + ", " + obj.birth;
 */