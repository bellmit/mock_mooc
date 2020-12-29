<template>
  <div class="pagination" role="group" aria-label="pagination">
    <button type="button" class="btn btn-default btn-white btn-round"
            v-bind:disabled="page===1"
            v-on:click="selectPage(1)">
      1
    </button>

    <button type="button" class="btn btn-default btn-white btn-round"
            v-bind:disabled="page===1"
            v-on:click="selectPage(page-1)">
      previous page
    </button>

    <button v-for="p in pages" v-bind:id="'page-'+p"
            type="button" class="btn btn-default btn-white btn-round"
            v-bind:class="{'btn-primary active':page==p}"
            v-on:click="selectPage(p)">
      {{ p }}
    </button>

    <button type="button" class="btn btn-default btn-white btn-round"
            v-bind:disabled="page===pageTotal"
            v-on:click="selectPage(page+1)">
      next page
    </button>

    <button type="button" class="btn btn-default btn-white btn-round"
            v-bind:disabled="page===pageTotal"
            v-on:click="selectPage(pageTotal)">
      {{ pageTotal || 1 }}
    </button>
    &nbsp;
    <span class="m--padding--10">
      each page
      <select v-model="size">
        <option value="1">1</option>
        <option value="5">5</option>
        <option value="10">10</option>
        <option value="15">15</option>
      </select>
      items, {{ total }} in total
    </span>
  </div>
</template>

<script>
export default {
  name: 'pagination',
  props: {
    list: {
      type: Function,
      default: null
    },
    itemCount: Number //page number to show. E.g., there are 100 pages in total, show only 10 page numbers
  },
  data: function () {
    return {
      total: 0, // total items
      size: 10, // size of items on each page
      page: 0, // current page number
      pageTotal: 0, // total page numbers
      pages: [] // page numbers to show
    }
  },
  methods: {
    /**
     * render pagination component
     * @param page
     * @param total
     */
    render(page, total) {
      let _this = this;
      _this.page = page;
      _this.total = total;
      _this.pageTotal = Math.ceil(total / _this.size);
      _this.pages = _this.getPageItems(_this.pageTotal, page, _this.itemCount || 5);
    },

    /**
     * query for a certain page
     */
    selectPage(page) {
      let _this = this;
      if (page < 1) {
        page = 1;
      }
      if (page > _this.pageTotal) {
        page = _this.pageTotal;
      }
      if (this.page != page) {
        _this.page = page;
        if (_this.list) {
          _this.list(page);
        }
      }
    },

    /**
     * page numbers to show
     * @param total: pageTotal
     * @param current: current page
     * @param length: how many pages to show
     * @returns {[]}
     */
    getPageItems(total, current, length) {
      let items = [];
      if (length >= total) {
        for (let i = 1; i <= total; i++) {
          items.push(i);
        }
      } else {
        let start = 0;
        if (current - 0 > Math.floor((length - 1) / 2)) {
          start = Math.min(total, current - 0 + Math.ceil((length - 1) / 2)) - length;
          console.log('start', start)
        }
        for (let i = 1; i <= length; i++) {
          items.push(start + i);
        }
      }
      return items;
    }
  }
}
</script>

<style scoped>
.pagination {
  vertical-align: middle !important;
  font-size: 16px;
  margin-top: 0;
  margin-bottom: 10px;
}

.pagination button {
  margin-right: 5px;
}

.btn-primary.active {
  background-color: #2f7bba !important;
  border-color: #27689d !important;
  color: white !important;
  font-weight: 600;
}
</style>