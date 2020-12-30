Validator = {
  require: function (value, text){
    if(Tool.isEmpty(value)){
      Toast.warning(text + " cannot be empty");
      return false;
    } else {
      return true;
    }
  },

  length: function (value, text, min, max){
    if(!Tool.isLength(value, min, max)){
      Toast.warning(text + " should be between " + min + " and " + max + " characters");
      return false;
    } else {
      return true;
    }
  }
}