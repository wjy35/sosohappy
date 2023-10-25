import RNSecureStorage from "rn-secure-storage";
import indexStore from "../store/indexStore";

export const logout = async () => {
	const {userStore} : any = indexStore();
	userStore.LoginStore.setLogged(false);
	await RNSecureStorage.remove("accessToken");
	await RNSecureStorage.remove("refreshToken");
	for (let key in userStore) {
		userStore[key] = {};
	}
};
