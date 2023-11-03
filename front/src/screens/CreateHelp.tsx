import CommonLayout from "@/components/CommonLayout";
import CreateHelpSelectCategory from "@/components/CreateHelpSelectCategory";

const CreateHelp = () => {
  return (
    <CommonLayout footer={true} headerType={0} nowPage={'Help'}>
      <CreateHelpSelectCategory/>
    </CommonLayout>
  );
};

export default CreateHelp;
