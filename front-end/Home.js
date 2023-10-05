import './Home.css'
import img1 from '../img/dog5.jpg'
import img2 from '../img/cat3.jpg'

function Home() {
  return (
    <div className="container">
      <div className="box_1">
        <div>
          Welcome to Dog&Cat Platform
        </div>
      </div>
      <div className="box_4">
        <div>Chào mừng bạn đến với trang <span className='box_4_span'>Dog&Cat Platform</span> của chúng tôi. Trang này là một nơi dành cho những người đam mê và yêu quý chó và mèo, hai loài vật cưng thân thuộc và gắn bó mật thiết với cuộc sống của chúng ta.

          <br />Chó và mèo không chỉ là những người bạn đáng yêu, mà còn là những đồng hành trung thành trong hành trình cuộc sống. Chúng đã tồn tại bên chúng ta hàng ngàn năm, từ những ngày đầu của sự thuần hóa cho đến ngày nay. Trong suốt thời gian đó, họ đã đồng hành cùng con người qua những thăng trầm, làm cho cuộc sống của chúng ta trở nên phong phú hơn và đáng yêu hơn.

          <br />Tại đây, bạn sẽ tìm thấy những câu chuyện thú vị về chó và mèo, cách chăm sóc và nuôi dưỡng họ, cũng như những thông tin hữu ích về giống loài, tình yêu thương, và cuộc sống hàng ngày của họ. Chúng tôi cũng sẽ cung cấp lời khuyên hữu ích cho những người mới bắt đầu hoặc những ai muốn tìm hiểu thêm về thế giới đáng yêu của chó và mèo.

          <br />Hãy cùng nhau khám phá và chia sẻ niềm đam mê với chó và mèo tại trang blog này. Chúng tôi hy vọng rằng trang web này sẽ trở thành một nguồn thông tin hữu ích và một nơi thú vị để kết nối với cộng đồng yêu thú cưng trên khắp thế giới. Cảm ơn bạn đã ghé thăm và chúng ta hãy cùng chia sẻ tình yêu đối với những người bạn bốn chân này!</div>

      </div>
      <div className="box_2">
        <div>
        <span className='box_span'>Một số điều về loài chó có thể bạn chưa biết hoặc đã biết</span>
          <br/>Chó được gọi là "người bạn thân thiết của con người" do khả năng hỗ trợ và bảo vệ chủ nhân của họ
          <br />Chó có thể có hơn 300 dạng khác nhau, từ các giống nhỏ như Chihuahua đến các giống lớn như Newfoundland.
        </div>
        <img src={img1} />
      </div>
      <div className="box_3">
        <img src={img2} />
        <div>
        <span className='box_span'>Một số điều về loài mèo có thể bạn chưa biết hoặc đã biết</span>
          <br/>Mèo có khả năng nhảy cao hơn nhiều lần so với chiều cao của họ và có thể đạt được độ cao tới 6 lần chiều cao của họ.
          <br />Mèo có thể phát ra hơn 100 loại âm thanh khác nhau, trong khi chó thường chỉ có khoảng 10 loại âm thanh.
        </div>
      </div>
      <a href='' className='test'>Nhấn vào đây để biết thêm nhiều điều thú vị hơn của hai anh bạn ở trên nào!</a>

    </div>

  );
}

export default Home;