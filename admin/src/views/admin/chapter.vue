<template>
  <div>
    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        Add chapter
      </button>
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh red2"></i>
        Refresh
      </button>

    </p>

    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount=5></pagination>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>id</th>
        <th>name</th>
        <th>course id</th>
        <th>action</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="chapter in chapters">
        <td>{{ chapter.id }}</td>
        <td>{{ chapter.name }}</td>
        <td>{{ chapter.courseId }}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(chapter)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(chapter.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
        </td>
      </tr>

      </tbody>
    </table>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">New chapter</h4>
          </div>
          <div class="modal-body">
            <form>
              <div class="form-group">
                <label>name</label>
                <input v-model="chapter.name" class="form-control" placeholder="chapter name">
              </div>
              <div class="form-group">
                <label>course id</label>
                <input v-model="chapter.courseId" class="form-control" placeholder="course id">
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">Save</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  </div>

</template>

<script>
import Pagination from "../../components/pagination";

export default {
  components: {Pagination},
  name: "chapter",
  data: function () {
    return {
      chapter: {}, //new chapter
      chapters: []
    }
  },
  mounted: function () {
    let _this = this;
    _this.$refs.pagination.size = 5;
    _this.list(1);
  },
  methods: {
    add() {
      let _this = this;
      _this.chapter = {};
      $("#form-modal").modal("show");
    },

    edit(chapter) {
      let _this = this;
      _this.chapter = $.extend({}, chapter);
      $("#form-modal").modal("show");
    },

    list(page) {
      let _this = this;
      Loading.show();
      _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/list', {
        page: page,
        size: _this.$refs.pagination.size
      })
          .then((response) => {
            Loading.hide();
            let resp = response.data;
            _this.chapters = resp.content.list;
            _this.$refs.pagination.render(page, resp.content.total);
          })
    },

    save() {
      let _this = this;
      //validation
      if (!Validator.require(_this.chapter.name, "chapter name")
          || !Validator.require(_this.chapter.courseId, "course id")
          || !Validator.length(_this.chapter.courseId, "course id", 1, 8)) {
        return;
      }
      Loading.show();
      _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/save',
          _this.chapter
      )
          .then((response) => {
            Loading.hide();
            let resp = response.data
            if (resp.success) {
              $("#form-modal").modal("hide");
              _this.list(1);
              Toast.success("A new item has been successfully saved!")
            } else {
              Toast.warning(resp.message);
            }
          })
    },

    del(id) {
      let _this = this;
      Confirm.show("The deleted chapter cannot be reverted!", function () {
        Loading.show();
        _this.$ajax.delete('http://127.0.0.1:9000/business/admin/chapter/delete/' + id)
            .then((response) => {
              Loading.hide();
              let resp = response.data
              if (resp.success) {
                _this.list(1);
                Toast.success("You deleted an item!")
              }
            })
      })
    }
  }
}
</script>