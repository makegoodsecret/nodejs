var thrift =require('thrift');
var ttransport = require('transport');
var assert = require('assert');

var UserService = require('../gen-nodejs/UserService.js');
var ttypes = require('../gen-nodejs/xuser_types');
var connection = thrift.createConnection('127.0.0.1', 3000, { 'transport': ttransport.TFramedTransport });
var client = thrift.createClient(UserService, connection);
 
connection.on("error",function(e)
{
  assert(false, err);
	console.log(e);
});
 
var x=new ttypes.User({
    uid:'112',
    uname:'aab',
    usex:0,
    uage:'181'
});
client.add(x,function(err, res){
    console.log("ADD OK1");
    client.get('112',function(err, res){
        if (err) {
            console.error(err);
        } else {
            console.log("Res:",res);
            connection.end();
        }
    });
    client.adduname('112',function(err, res){
        if (err) {
            console.error(err);
        } else {
            console.log("Res:",res);
            connection.end();
        }
    });
});