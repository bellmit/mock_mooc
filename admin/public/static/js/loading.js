Loading = {
  show: function () {
    $.blockUI({
      message: '<img src="/static/image/loading.gif" />',
      css: {
        padding: "10px",
        left: "50%",
        width: "80px",
        marginLeft: "-40px"
      }
    });
  },

  hide: function () {
    //$.unblockUI();
    //use timeout in testing to show the loading gif more clearly (the
    // query will be very fast since both server and db are located locally)
    setTimeout(function () {
      $.unblockUI();
    }, 500)
  }
}