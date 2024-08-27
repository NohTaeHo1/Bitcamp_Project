import { Button } from "@mui/material";

declare global {
  interface Window {
    daum: any;
  }
}

interface IAddr {
  address: string;
  zonecode: string;
}

export default function Addr() {
  const onClickAddr = () => {
    new window.daum.Postcode({
      oncomplete: function (data: IAddr) {
        (document.getElementById("addr") as HTMLInputElement).value =
          data.address;
        (document.getElementById("zipNo") as HTMLInputElement).value =
          data.zonecode;
        document.getElementById("addrDetail")?.focus();
      },
    }).open();
  };

  return (
    <>
      <input
        id="addr"
        type="text"
        readOnly
        onClick={onClickAddr}
        style={{ width: "200px", marginRight: "10px" }}
      />
      <Button variant="contained" size="large">
        <button onClick={onClickAddr}>우편번호 검색</button>
      </Button>

      <input id="zipNo" type="text" readOnly />
      <input id="addrDetail" type="text" />
    </>
  );
}
