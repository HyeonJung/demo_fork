let account;
let mintIndexForSale = 0;
let maxSaleAmount = 0;
let mintPrice = 0;
let mintStartBlockNumber = 0;
let mintLimitPerBlock = 0;

let blockNumber = 0;
let blockCnt = false;

function cntBlockNumber() {
    if(!blockCnt) {
        setInterval(function(){
            blockNumber+=1;
            document.getElementById("blockNubmer").innerHTML = "현재 블록: #" + blockNumber;
        }, 1000);
        blockCnt = true;
    }
}

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
            document.getElementById("myWallet").innerHTML = `지갑주소: ${account}`
            document.getElementById("myKlay").innerHTML = `잔액: ${caver.utils.fromPeb(balance, "KLAY")} KLAY`
        });

    //토큰생성
    /*
    caver.klay.KIP7.deploy({
        name: 'BigDG',
        symbol: 'DG',
        decimals: 18,
        initialSupply: '100000000000000000000000',
    },'0x108ED2F0580d6eF1461549d25EA6bcBE7c947189').then(console.log);
    */

/*    const caver2 = new caver.contract(ABI, CONTRACTADDRESS);
    caver2.providers()
    let me = await caver2.methods.balanceOf('0x108ED2F0580d6eF1461549d25EA6bcBE7c947189').call();
    await caver2.methods.approve(
        '0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072',
        "5").send({from:'0x108ED2F0580d6eF1461549d25EA6bcBE7c947189',
                   gas: '21000'});

    await caver2.methods.transferFrom('0x∂108ED2F0580d6eF1461549d25EA6bcBE7c947189',
                                                    '0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072',
                                                    5).send({from:'0x108ED2F0580d6eF1461549d25EA6bcBE7c947189'});
    console.log(caver2.methods.balanceOf('0x108ED2F0580d6eF1461549d25EA6bcBE7c947189').call());*/
    //await check_status();
}

async function check_status() {
    const myContract = new caver.klay.Contract(ABI, CONTRACTADDRESS);
    await myContract.methods.mintingInformation().call()
        .then(function (result) {
            console.log(result);
            mintIndexForSale = parseInt(result[1]);
            mintLimitPerBlock = parseInt(result[2]);
            mintStartBlockNumber = parseInt(result[4]);
            maxSaleAmount = parseInt(result[5]);
            mintPrice = parseInt(result[6]);
            document.getElementById("mintCnt").innerHTML = `${mintIndexForSale - 1} / ${maxSaleAmount}`;
            document.getElementById("mintLimitPerBlock").innerHTML = `트랜잭션당 최대 수량: ${mintLimitPerBlock}개`;
            document.getElementById('amount').max = mintLimitPerBlock;
            document.getElementById("mintStartBlockNumber").innerHTML = `민팅 시작 블록: #${mintStartBlockNumber}`;
            document.getElementById("mintPrice").innerHTML = `민팅 가격: ${caver.utils.fromPeb(mintPrice, "KLAY")} KLAY`;
        })
        .catch(function (error) {
            console.log(error);
        });
    blockNumber = await caver.klay.getBlockNumber();
    document.getElementById("blockNubmer").innerHTML = "현재 블록: #" + blockNumber;
    cntBlockNumber();
}

async function publicMint() {
    if (klaytn.networkVersion === 8217) {
        console.log("메인넷");
    } else if (klaytn.networkVersion === 1001) {
        console.log("테스트넷");
    } else {
        alert("ERROR: 클레이튼 네트워크로 연결되지 않았습니다!");
        return;
    }
    if (!account) {
        alert("ERROR: 지갑을 연결해주세요!");
        return;
    }

    const myContract = new caver.klay.Contract(ABI, CONTRACTADDRESS);
    const amount = document.getElementById('amount').value;
    await check_status();
    if (maxSaleAmount + 1 <= mintIndexForSale) {
        alert("모든 물량이 소진되었습니다.");
        return;
    } else if (blockNumber <= mintStartBlockNumber) {
        alert("아직 민팅이 시작되지 않았습니다.");
        return;
    }
    const total_value = BigNumber(amount * mintPrice);

    try {
        const gasAmount = await myContract.methods.publicMint(amount).estimateGas({
            from: account,
            gas: 6000000,
            value: total_value
        })
        const result = await myContract.methods.publicMint(amount).send({
            from: account,
            gas: gasAmount,
            value: total_value
        })
        if (result != null) {
            console.log(result);
            alert("민팅에 성공하였습니다.");
        }
    } catch (error) {
        console.log(error);
        alert("민팅에 실패하였습니다.");
    }
}

async function transfer(){
    //const ins = new caver.klay.KIP7('0x0e7606FDD6d1F7925ed65e1E3BEe67F38363A87a');

    //console.log(ins);
    const ins = new caver.klay.KIP17('0xDAe19b054145edde85378Df3d9c144fDA89376cF');
    await ins.balanceOf('0x4c04f8F23B91785aba7B75c32EbB32618DE25565').then(console.log);
    await ins.transferFrom('0x4c04f8F23B91785aba7B75c32EbB32618DE25565', '0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072', 1, {from:'0x4c04f8F23B91785aba7B75c32EbB32618DE25565'}).then(console.log)

    //await ins.balanceOf('account').then(console.log);
    //await ins.allowance('0x108ED2F0580d6eF1461549d25EA6bcBE7c947189', '0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072').then(console.log);
    //await ins.approve('0x108ED2F0580d6eF1461549d25EA6bcBE7c947189', 10000000000000000000000, {from:'0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072'}).then(console.log)
    //await ins.allowance('0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072', '0x108ED2F0580d6eF1461549d25EA6bcBE7c947189').then(console.log);
    //await ins.transferFrom('0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072', '0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072', 50000, {from:'0x108ED2F0580d6eF1461549d25EA6bcBE7c947189'}).then(console.log)

    //const pam = await ins.transfer('0x4c04f8F23B91785aba7B75c32EbB32618DE25565', 100000000000000000000, { from: account }).then(console.log)

}
