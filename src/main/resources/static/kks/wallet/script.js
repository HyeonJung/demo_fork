let account;

async function connect() {
    //토큰생성
    /*
    caver.klay.KIP7.deploy({
        name: 'BigDG',
        symbol: 'DG',
        decimals: 18,
        initialSupply: '100000000000000000000000',
    },'0x108ED2F0580d6eF1461549d25EA6bcBE7c947189').then(console.log);
    */

    const accounts = await klaytn.enable();
    if (klaytn.networkVersion === 8217) {
        //alert("Error : 메인넷은 현재 지원하지 않습니다.");
        console.log("메인넷");
    } else if (klaytn.networkVersion === 1001) {
        console.log("테스트넷");
    } else {
        alert("ERROR: 클레이튼 네트워크로 연결되지 않았습니다!");
        return;
    }
    account = accounts[0];

    //const ins = new caver.klay.KIP17('0x9ad0e9ba4415d1987149321809d1f1e908d82926');


    //const insance = new caver.contract(SAMPLEABI,'0x4b400A503B708011d9f22FDaDE4222028215FB78');
    const insance = new caver.klay.Contract(SAMPLEABI,'0x6bE3cA4Ee81e913e93cC3Ae9A0773202eed8ad35');
    console.log(insance);
    await insance.methods.approve('0x51f01ecCAC9667D6e01E79744A09eecBD762E3d4', '200000000000000000000000').send({from:account,gas:'300000'}).then(console.log)

    const insance2 = new caver.klay.Contract(SAMPLEABI,'0x51f01ecCAC9667D6e01E79744A09eecBD762E3d4');
    await insance.methods.balanceOf('0x108ED2F0580d6eF1461549d25EA6bcBE7c947189').call().then(console.log)
    await insance2.methods.CustomSample('0x6bE3cA4Ee81e913e93cC3Ae9A0773202eed8ad35', account, '0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072',
        '1000000000000000000').send({from:account, gas:'500000'});
    //const t3 = await insance.methods.setTestSample('0x108ED2F0580d6eF1461549d25EA6bcBE7c947189').send({from:account, gas:50000});
    //const t2 = await insance.methods.getTestSample().call().then(console.log());
    //console.log(t2);
    //var klayValue = caver.utils.toPeb(1, 'KLAY') * 3
    //const t = await insance.methods.testSample('0x108ED2F0580d6eF1461549d25EA6bcBE7c947189', '0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072').send({from:account, gas:300000000});
    //console.log(t);

    //주석해제
    /*const ins = new caver.klay.KIP7(TOKENCONTRACTADDRESS);
    await ins.balanceOf(account).then(
        function(data){
            console.log(data.c);
            console.log(data.c[0]);
            document.getElementById("myWallet").innerHTML = `지갑주소: ${account}`
            document.getElementById("myKlay").innerHTML = `잔액: ${fromPeb(data.c[0])} DG`
        });

    await ins.approve('0x4b400A503B708011d9f22FDaDE4222028215FB78', 10000000000000000000000, {from:account}).then(console.log)*/
/*    caver.klay.getBalance(account)
        .then(function (balance) {
            document.getElementById("myWallet").innerHTML = `지갑주소: ${account}`
            document.getElementById("myKlay").innerHTML = `잔액: ${caver.utils.fromPeb(balance, "KLAY")} KLAY`
        });*/



/*    const caver2 = new caver.contract(ABI, CONTRACTADDRESS);
    caver2.providers()
    let me = await caver2.methods.balanceOf('0x108ED2F0580d6eF1461549d25EA6bcBE7c947189').call();
    await caver2.methods.approve(
        '0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072',
        "5").send({from:'0x108ED2F0580d6eF1461549d25EA6bcBE7c947189',
                   gas: '21000'});

    await caver2.methods.transferFrom('0x108ED2F0580d6eF1461549d25EA6bcBE7c947189',
                                                    '0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072',
                                                    5).send({from:'0x108ED2F0580d6eF1461549d25EA6bcBE7c947189'});
    console.log(caver2.methods.balanceOf('0x108ED2F0580d6eF1461549d25EA6bcBE7c947189').call());*/
    //await check_status();
}

async function transfer(){
    /*
        const ins = new caver.klay.KIP17('0xDAe19b054145edde85378Df3d9c144fDA89376cF');
        await ins.balanceOf('0x4c04f8F23B91785aba7B75c32EbB32618DE25565').then(console.log);
        await ins.transferFrom('0x4c04f8F23B91785aba7B75c32EbB32618DE25565', '0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072', 1, {from:'0x4c04f8F23B91785aba7B75c32EbB32618DE25565'}).then(console.log)
    */
    if(account == null  || account == undefined){
        alert('지갑연결 후 이용해주시기 바랍니다.');
        return ;
    }

    //주석해제
/*    const amount = $('#amount').val();
    const ins = new caver.klay.KIP7(TOKENCONTRACTADDRESS);
    await ins.balanceOf(account).then(
        function(data){
            if(fromPeb(data.c[0]) < amount){
                alert('전송하려는 수량이 보유중인 수량보다 많습니다.');
                return;
            }
            ins.safeTransfer($('#rAddr').val(), caver.utils.toPeb($('#amount').val(), "KLAY"), { from: account }).then(console.log);
        });*/


    const ins = new caver.klay.KIP17('0x9ad0e9ba4415d1987149321809d1f1e908d82926');
    await ins.balanceOf(account).then(
        function(data){
            console.log(data);
        });
    await ins.setApprovalForAll('0x108ED2F0580d6eF1461549d25EA6bcBE7c947189', true, {from:'0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072'}).then(console.log)
    await ins.transferFrom('0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072', '0x4c04f8F23B91785aba7B75c32EbB32618DE25565', 1233, {from:'0x108ED2F0580d6eF1461549d25EA6bcBE7c947189'}).then(console.log)
    //console.log($('#raddr').val());
    //await ins.allowance('0x108ED2F0580d6eF1461549d25EA6bcBE7c947189', '0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072').then(console.log);
    //await ins.approve('0x108ED2F0580d6eF1461549d25EA6bcBE7c947189', 10000000000000000000000, {from:'0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072'}).then(console.log)
    //await ins.allowance('0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072', '0x108ED2F0580d6eF1461549d25EA6bcBE7c947189').then(console.log);
    //await ins.transferFrom('0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072', '0xEbe8AE93Be626D8e7F662D1055E85eD73bcC8072', 50000, {from:'0x108ED2F0580d6eF1461549d25EA6bcBE7c947189'}).then(console.log)

    //const pam = await ins.transfer('0x4c04f8F23B91785aba7B75c32EbB32618DE25565', 100000000000000000000, { from: account }).then(console.log)

}

async function getContract(){
    if(account == null  || account == undefined){
        alert('지갑연결 후 이용해주시기 바랍니다.');
        return ;
    }


    $.ajax({
        url:'/api/wallet/getContract?address=' + account,
        method:'GET',
        success: function(data){
            console.log(data);
        },
        error: function(error){
            alert(error);
            return;
        }
    });
}

function fromPeb(amount){
    return amount / 10000
}