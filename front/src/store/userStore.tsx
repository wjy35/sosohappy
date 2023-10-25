import {observable} from "mobx";

const userStore = observable({
  user: {name: 'gdsadgas'},
  // *getUser(){
  //   try {
  //     const member = yield memberApi.getUser();
  //     this.user = member.data.data;
  //     return member
  //   } catch (err) {
  //     console.log(err);
  //   }
  // },
});

export default userStore;
