import './Home.css'
import img1 from '../img/dog5.jpg'
import img2 from '../img/cat3.jpg'

function Home() {
  return (
    <div className="container">
      <div className="box_1">
        <div>
          Wellcome to Dog&Cat Platform
        </div>
      </div>
      <div className="box_2">
        <div>
          <span className='box_span'>Chó</span>là một loài động vật thuộc họ Canidae và loài Canis lupus familiaris, có nguồn gốc từ loài sói hoang dã. Chúng là một trong những loài động vật thân thiện và thân thuộc nhất với con người và đã được nuôi dưỡng và tạo thành một phần quan trọng trong cuộc sống và văn hóa của chúng ta.

          <br /> Chó có sự đa dạng về kích thước, hình dáng và màu lông do quá trình lai tạo kéo dài hàng ngàn năm để tạo ra các giống chó khác nhau. Chúng có thể làm công việc đa dạng như canh gác, săn bắn, kéo xe, giúp việc, và nhiều công việc khác. Chó cũng thường được nuôi làm thú cưng để làm bạn đồng hành, bảo vệ và cung cấp niềm vui cho con người.

          <br />Chó có tầm nhìn và khả năng mũi rất tốt, và chúng có khả năng phát hiện mùi và âm thanh mà con người không thể cảm nhận được. chúng là những sinh vật xã hội và thường xây dựng mối quan hệ đặc biệt với chủ nhân của chúng. Chó được tạo ra và nuôi dưỡng trên khắp thế giới và có hàng trăm giống khác nhau với đặc điểm riêng biệt.
        </div>
        <img src={img1} />
      </div>
      <div className="box_3">
        <img src={img2} />
        <div>
          <span className='box_span'>Mèo</span> là một loài động vật thuộc họ Felidae và loài Felis catus. Mèo là một loài động vật có nguồn gốc từ loài mèo hoang dã và đã được nuôi dưỡng và tạo thành một phần quan trọng trong cuộc sống và văn hóa của con người.

          <br />  Mèo thường có kích thước nhỏ đến trung bình, với thân hình linh hoạt, tai nhọn và lưỡi dài có khả năng liếm lông của họ. Họ thường có bộ lông mềm mịn và có nhiều màu sắc và hoa văn khác nhau. Mèo có tầm nhìn sắc nét và khả năng nghe rất tốt, giúp họ săn bắn và bắt mồi hiệu quả trong tự nhiên.

          <br /> Mèo thường được nuôi làm thú cưng để làm bạn đồng hành, cung cấp niềm vui cho gia đình và giữ sự cân bằng trong cuộc sống hàng ngày của con người. Họ thường thể hiện tính cách độc lập và có thể trở thành thành viên quan trọng trong gia đình. Mèo cũng nổi tiếng với khả năng tự làm sạch lông bằng cách liếm.

          <br /> Trên khắp thế giới, có hàng loạt các giống mèo khác nhau với đặc điểm riêng biệt về màu sắc, kích thước và tính cách. Mèo có vai trò quan trọng trong nhiều tín ngưỡng tôn thờ và thần thoại, và họ đã xuất hiện trong nhiều tác phẩm văn hóa, nghệ thuật và văn học khác nhau.
        </div>
      </div>
      <div className="box_4">
        <div>Chào mừng bạn đến với trang <span className='box_4_span'>Dog&Cat Platform</span> của chúng tôi. Trang này là một nơi dành cho những người đam mê và yêu quý chó và mèo, hai loài vật cưng thân thuộc và gắn bó mật thiết với cuộc sống của chúng ta.

          <br />Chó và mèo không chỉ là những người bạn đáng yêu, mà còn là những đồng hành trung thành trong hành trình cuộc sống. Chúng đã tồn tại bên chúng ta hàng ngàn năm, từ những ngày đầu của sự thuần hóa cho đến ngày nay. Trong suốt thời gian đó, họ đã đồng hành cùng con người qua những thăng trầm, làm cho cuộc sống của chúng ta trở nên phong phú hơn và đáng yêu hơn.

          <br />Tại đây, bạn sẽ tìm thấy những câu chuyện thú vị về chó và mèo, cách chăm sóc và nuôi dưỡng họ, cũng như những thông tin hữu ích về giống loài, tình yêu thương, và cuộc sống hàng ngày của họ. Chúng tôi cũng sẽ cung cấp lời khuyên hữu ích cho những người mới bắt đầu hoặc những ai muốn tìm hiểu thêm về thế giới đáng yêu của chó và mèo.

          <br />Hãy cùng nhau khám phá và chia sẻ niềm đam mê với chó và mèo tại trang blog này. Chúng tôi hy vọng rằng trang web này sẽ trở thành một nguồn thông tin hữu ích và một nơi thú vị để kết nối với cộng đồng yêu thú cưng trên khắp thế giới. Cảm ơn bạn đã ghé thăm và chúng ta hãy cùng chia sẻ tình yêu đối với những người bạn bốn chân này!</div>
        <a href='' className='test'>Tìm hiểu thêm về web của chúng tôi!!</a>
      </div>

    </div>

  );
}

export default Home;