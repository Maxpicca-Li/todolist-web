<template>

</template>

<script>
  //手机号格式验证
  export const validatePhone = function (rule, value, callback) {
    if (value != '') {
      var reg = /(^1\d{10}$)|(^[0-9]\d{7}$)/;
      var re = new RegExp(reg);
      if (!re.test(value)) {
        callback(new Error('手机号格式不正确'));
      } else {
        callback();
      }
    } else {
      callback();
    }
  };

  export const validateEmail =  (rule, value, callback)=>{
    if (value != '') {
      if (value.length > 50) {
        callback(new Error('邮箱字符长度不能超过50个字符'));
      }
      var reg=/[A-Za-z0-9._%+-]+@@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}/;
      var re=new RegExp(reg);
      if(!re.test(value)){
        callback(new Error('邮箱格式不正确'));
      }
      else{
        callback();
      }
    }
  }

  /* 邮箱 */
  export function checkEmail(str) {
    const reg=/[A-Za-z0-9._%+-]+@@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}/;
    return reg.test(str)
  }
  /* 银行账户 */
  export function validateAccountNumber(str) {
    const reg = /^([1-9]{1})(\d{14}|\d{18})$/
    return reg.test(str)
  }

</script>

<style scoped>

</style>