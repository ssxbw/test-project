<template>
  <!-- <img id="icon" @click="pop" src="../style/icon.png"> -->
  <img id="icon" src="../style/icon.png">
</template>

<style>
#icon{
  position:relative;
  top:15px;
  left:50px;
  float: left;
  width: 60px;
  height:60px;
  border-radius: 100%;
  transition: all 0.6s;
}
#icon:hover{
  transform: rotate(-360deg);
}
</style>



<script>
// @ is an alias to /src

export default {
name: 'IconView',
components: {

},
data(){
  return {
    flag:0,
  }
},
methods:{
  pop(){
    if(this.flag==0){
      this.flag=1;
      var div = document.createElement('div');
      div.setAttribute("style", "float:left;width:40;height:20;color:pink");
      alert("ajsdgk");
    }
  }
},
props: {
  msg: String,
  icon_path: String
}
}
</script>