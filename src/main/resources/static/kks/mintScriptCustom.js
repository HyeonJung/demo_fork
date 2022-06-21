let account;
let mintIndexForSale = 0;
let maxSaleAmount = 0;
let mintPrice = 0;
let mintStartBlockNumber = 0;
let mintLimitPerBlock = 0;

let blockNumber = 0;
let blockCnt = false;

async function connect() {
    const accounts = await klaytn.enable();
    if (klaytn.networkVersion === 8217) {
        console.log("메인넷");
    } else if (klaytn.networkVersion === 1001) {
        console.log("테스트넷");
    } else {
        alert("ERROR: 클레이튼 네트워크로 연결되지 않았습니다!");
        return;
    }
    account = accounts[0];
    caver.klay.getBalance(account)
        .then(function (balance) {
            var address = `${account}`;
            $('#wallet').text(cutAddress(address));
            $('#kalyAmount').text(priceToString(Math.round(`${caver.utils.fromPeb(balance, "KLAY")}`)) + "KLAY");

        });
    //await check_status();
}
function priceToString(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

function cutAddress(address){
    let result = address.substring(0,10) + '....' + address.substring(address.length-4, address.length)
    return result;
}
